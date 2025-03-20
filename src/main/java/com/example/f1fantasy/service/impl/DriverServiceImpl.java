package com.example.f1fantasy.service.impl;

import com.example.f1fantasy.client.OpenF1Client;
import com.example.f1fantasy.exception.DataNotFoundException;
import com.example.f1fantasy.exception.DriverAlreadyExistsException;
import com.example.f1fantasy.exception.ExternalApiException;
import com.example.f1fantasy.mapper.DriverMapper;
import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.dto.external.ExternalDriverDataDTO;
import com.example.f1fantasy.model.dto.external.ExternalMeetingDataDTO;
import com.example.f1fantasy.model.dto.external.ExternalSessionDataDTO;
import com.example.f1fantasy.model.dto.filter.DriverFilterDTO;
import com.example.f1fantasy.model.dto.util.ConflictInfoDTO;
import com.example.f1fantasy.model.dto.util.DriverCreationResponseDTO;
import com.example.f1fantasy.model.entity.Driver;
import com.example.f1fantasy.model.enums.SessionTypeEnum;
import com.example.f1fantasy.repository.DriverRepository;
import com.example.f1fantasy.repository.specification.DriverSpecification;
import com.example.f1fantasy.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final OpenF1Client openF1Client;

    @Override
    @Transactional(readOnly = true)
    public List<DriverDTO> getDrivers(DriverFilterDTO driverFilterDTO) {

        Specification<Driver> specification = Specification
                .where(DriverSpecification.hasDriverId(driverFilterDTO.driverId()))
                .and(DriverSpecification.hasBroadcastName(driverFilterDTO.broadcastName()))
                .and(DriverSpecification.hasDriverNumber(driverFilterDTO.driverNumber()))
                .and(DriverSpecification.hasFirstName(driverFilterDTO.firstName()))
                .and(DriverSpecification.hasLastName(driverFilterDTO.lastName()))
                .and(DriverSpecification.hasFullName(driverFilterDTO.fullName()))
                .and(DriverSpecification.hasCountryCode(driverFilterDTO.countryCode()))
                .and(DriverSpecification.hasNameAcronym(driverFilterDTO.nameAcronym()));

        return driverRepository.findAll(specification).stream().map(driverMapper::toDTO).toList();
    }

    @Override
    public DriverDTO createDriver(DriverDTO driverDTO) {

        validateDriverDTO(driverDTO);

        List<String> conflictReasons = new ArrayList<>();

        if (driverRepository.existsByFullName(driverDTO.getFullName())) {
            conflictReasons.add("A driver with the same name already exists: " + driverDTO.getFullName());
        }

        if (driverRepository.existsByDriverNumber(driverDTO.getDriverNumber())) {
            conflictReasons.add("A driver with the same driver number already exists: " + driverDTO.getDriverNumber());
        }

        if (driverRepository.existsByNameAcronym(driverDTO.getNameAcronym())) {
            conflictReasons.add("A driver with the same name acronym already exists: " + driverDTO.getNameAcronym());
        }

        // Если есть причины, выбрасываем исключение
        if (!conflictReasons.isEmpty()) {
            String errorMessage = String.join("; ", conflictReasons);
            throw new DriverAlreadyExistsException(errorMessage);
        }

        Driver driverEntity = driverMapper.toEntity(driverDTO);

        Driver savedDriver = driverRepository.save(driverEntity);

        return driverMapper.toDTO(savedDriver);
    }

    @Override
    public DriverCreationResponseDTO createDriversViaF1API(Integer year) {
        // Получаем гонщиков из F1 API и убираем дубликаты
        List<ExternalDriverDataDTO> uniqueDrivers = fetchAndFilterDriversByYear(year);

        List<DriverDTO> driverDTOs = driverMapper.toDriverDTOListFromExternal(uniqueDrivers);

        // Проверяем поля на null и заменяем на n/a если пустые
        driverDTOs.forEach(this::handleNullFields);

        List<ConflictInfoDTO> conflicts = new ArrayList<>();
        List<Driver> driverEntities = new ArrayList<>();

        // Получаем список всех водителей, которые могут иметь тот же driverNumber или nameAcronym
        List<Driver> allDrivers = driverRepository.findAllByDriverNumberInAndNameAcronymIn(
                driverDTOs.stream().map(DriverDTO::getDriverNumber).toList(),
                driverDTOs.stream().map(DriverDTO::getNameAcronym).toList()
        );

        // Преобразуем список allDrivers в Map для быстрого поиска
        Map<Integer, Driver> driverNumberMap = allDrivers.stream()
                .collect(Collectors.toMap(Driver::getDriverNumber, driver -> driver));
        Map<String, Driver> nameAcronymMap = allDrivers.stream()
                .collect(Collectors.toMap(Driver::getNameAcronym, driver -> driver));

        // Обрабатываем каждый DriverDTO
        for (DriverDTO driverDTO : driverDTOs) {
            Optional<Driver> existingDriverByNumber = Optional.ofNullable(driverNumberMap.get(driverDTO.getDriverNumber()));
            Optional<Driver> existingDriverByAcronym = Optional.ofNullable(nameAcronymMap.get(driverDTO.getNameAcronym()));

            // Если найден дубликат driver_number с другим full_name, добавляем в список конфликтов
            if (existingDriverByNumber.isPresent() && !existingDriverByNumber.get().getFullName().equals(driverDTO.getFullName())) {
                conflicts.add(
                        ConflictInfoDTO.builder()
                                .fullName(driverDTO.getFullName())
                                .nameAcronym(driverDTO.getNameAcronym())
                                .driverNumber(driverDTO.getDriverNumber())
                                .conflictReason("Duplicate driver number")
                                .build()
                );
            }

            // Если найден дубликат name_acronym с другим full_name, добавляем в список конфликтов
            if (existingDriverByAcronym.isPresent() && !existingDriverByAcronym.get().getFullName().equals(driverDTO.getFullName())) {
                conflicts.add(
                        ConflictInfoDTO.builder()
                                .fullName(driverDTO.getFullName())
                                .nameAcronym(driverDTO.getNameAcronym())
                                .driverNumber(driverDTO.getDriverNumber())
                                .conflictReason("Duplicate name acronym")
                                .build()
                );
            }

            // Если нет конфликта по driver_number и name_acronym, маппим и добавляем в список для сохранения
            if (existingDriverByNumber.isEmpty() && existingDriverByAcronym.isEmpty()) {
                driverEntities.add(driverMapper.toEntity(driverDTO));
            }
        }

        if (!driverEntities.isEmpty()) {
            driverRepository.saveAll(driverEntities);
        }

        return new DriverCreationResponseDTO(driverMapper.toDriverDTOListFromEntities(driverEntities), conflicts);
    }

    @Override
    public DriverDTO updateDriver(String fullName, DriverDTO driverDTO) {

        Driver existingDriver = driverRepository.findByFullName(fullName)
                .orElseThrow(() -> new DataNotFoundException("Driver with fullName " + fullName + " not found"));

        List<String> conflictReasons = new ArrayList<>();

        // Проверяем, не занят ли новый driverNumber другим гонщиком
        if (driverDTO.getDriverNumber() != null &&
                driverRepository.existsByDriverNumberAndFullNameNot(driverDTO.getDriverNumber(), fullName)) {
            conflictReasons.add("A driver with the same driver number already exists: " + driverDTO.getDriverNumber());
        }

        // Проверяем, не занят ли новый nameAcronym другим гонщиком
        if (driverDTO.getNameAcronym() != null &&
                driverRepository.existsByNameAcronymAndFullNameNot(driverDTO.getNameAcronym(), fullName)) {
            conflictReasons.add("A driver with the same name acronym already exists: " + driverDTO.getNameAcronym());
        }

        // Если есть причины, выбрасываем исключение
        if (!conflictReasons.isEmpty()) {
            String errorMessage = String.join("; ", conflictReasons);
            throw new DriverAlreadyExistsException(errorMessage);
        }

        driverMapper.updateEntityFromDTO(driverDTO, existingDriver);

        Driver updatedDriver = driverRepository.save(existingDriver);

        return driverMapper.toDTO(updatedDriver);
    }

    @Override
    @Transactional
    public List<DriverDTO> updateDriversViaF1API(Integer year) {
        // Получаем гонщиков из F1 API и убираем дубликаты
        List<ExternalDriverDataDTO> uniqueDrivers = fetchAndFilterDriversByYear(year);

        // Получаем список текущих записей пилотов из БД по полному имени
        List<Driver> existingDrivers = driverRepository.findAllByFullNameIn(
                uniqueDrivers.stream().map(ExternalDriverDataDTO::getFullName).toList()
        );

        // Создаем мапу <fullName, Driver> для удобного поиска существующих записей
        Map<String, Driver> existingDriverMap = existingDrivers.stream()
                .collect(Collectors.toMap(Driver::getFullName, driver -> driver));

        List<Driver> updatedDrivers = new ArrayList<>();

        // Обновляем данные для каждого уникального гонщика
        for (ExternalDriverDataDTO externalDriver : uniqueDrivers) {
            Driver existingDriver = existingDriverMap.get(externalDriver.getFullName());

            if (existingDriver != null) {
                // Обновляем данные сущности с проверкой на изменения
                boolean isUpdated = updateDriverDataIfChanged(existingDriver, externalDriver);

                // Если ни одно поле не изменилось, пропускаем обновление
                if (!isUpdated) {
                    continue;
                }

                // Применяем обработку null значений
                handleNullFields(existingDriver);

                // Добавляем обновленного гонщика в список
                updatedDrivers.add(existingDriver);
            }
        }

        // Сохраняем обновленные записи в БД
        if (!updatedDrivers.isEmpty()) {
            driverRepository.saveAll(updatedDrivers);
        }

        // Возвращаем обновленные записи в виде DTO
        return driverMapper.toDriverDTOListFromEntities(updatedDrivers);
    }

    @Override
    @Transactional
    public void deleteDriver(String fullName) {
        Driver existingDriver = driverRepository.findByFullName(fullName)
                .orElseThrow(() -> new DataNotFoundException("Driver " + fullName + " not found"));

        driverRepository.delete(existingDriver);

        ResponseEntity.ok("Driver " + fullName + " deleted successfully");
    }

    private List<ExternalDriverDataDTO> fetchAndFilterDriversByYear(int year) {

        List<ExternalMeetingDataDTO> meetings;
        try {
            // Получаем список всех гонок за конкретный год
            meetings = openF1Client.getMeetingsByYear(year);
        } catch (ExternalApiException e) {
            throw new ExternalApiException("External API is unavailable. Endpoint https://api.openf1.org/v1/meetings");
        }

        if (meetings.isEmpty()) {
            throw new DataNotFoundException("No meetings found for " + year + ".");
        }

        // Берем meeting_key последнего события (последний состоявшийся гранд-при)
        int latestMeetingKey = meetings.get(meetings.size() - 1).getMeetingKey();

        List<ExternalSessionDataDTO> sessions;
        try {
            // Получаем список сессий по этому meeting_key
            sessions = openF1Client.getSessionsByMeetingKey(latestMeetingKey);
        } catch (ExternalApiException e) {
            throw new ExternalApiException("External API is unavailable. Endpoint https://api.openf1.org/v1/sessions");
        }

        // Ищем сессию с session_type == "Race"
        Optional<Integer> raceSessionKey = sessions.stream()
                .filter(session -> SessionTypeEnum.RACE.getValue().equalsIgnoreCase(session.getSessionType()))
                .map(ExternalSessionDataDTO::getSessionKey)
                .findFirst();

        if (raceSessionKey.isEmpty()) {
            throw new DataNotFoundException("No race session found for meeting_key " + latestMeetingKey);
        }

        List<ExternalDriverDataDTO> drivers;
        try {
            // Получаем список гонщиков по найденному session_key
            drivers = openF1Client.getDriversBySessionKey(raceSessionKey.get());
        } catch (ExternalApiException e) {
            throw new ExternalApiException("External API is unavailable. Endpoint https://api.openf1.org/v1/drivers");
        }

        // Убираем дубликаты по full_name, начиная с конца списка
        Map<String, ExternalDriverDataDTO> uniqueDrivers = new LinkedHashMap<>();
        for (int i = drivers.size() - 1; i >= 0; i--) {
            ExternalDriverDataDTO driver = drivers.get(i);
            uniqueDrivers.putIfAbsent(driver.getFullName(), driver);
        }

        return new ArrayList<>(uniqueDrivers.values());
    }

    private boolean updateDriverDataIfChanged(Driver existingDriver, ExternalDriverDataDTO externalDriver) {
        boolean isUpdated = false;

        // Обновляем поля, если они изменились и не равны "n/a"
        if (shouldUpdateField(existingDriver.getFirstName(), externalDriver.getFirstName())) {
            existingDriver.setFirstName(externalDriver.getFirstName());
            isUpdated = true;
        }
        if (shouldUpdateField(existingDriver.getLastName(), externalDriver.getLastName())) {
            existingDriver.setLastName(externalDriver.getLastName());
            isUpdated = true;
        }
        if (shouldUpdateField(existingDriver.getDriverNumber(), externalDriver.getDriverNumber())) {
            existingDriver.setDriverNumber(externalDriver.getDriverNumber());
            isUpdated = true;
        }
        if (shouldUpdateField(existingDriver.getBroadcastName(), externalDriver.getBroadcastName())) {
            existingDriver.setBroadcastName(externalDriver.getBroadcastName());
            isUpdated = true;
        }
        if (shouldUpdateField(existingDriver.getFullName(), externalDriver.getFullName())) {
            existingDriver.setFullName(externalDriver.getFullName());
            isUpdated = true;
        }
        if (shouldUpdateField(existingDriver.getNameAcronym(), externalDriver.getNameAcronym())) {
            existingDriver.setNameAcronym(externalDriver.getNameAcronym());
            isUpdated = true;
        }
        if (shouldUpdateField(existingDriver.getCountryCode(), externalDriver.getCountryCode())) {
            existingDriver.setCountryCode(externalDriver.getCountryCode());
            isUpdated = true;
        }
        if (shouldUpdateField(existingDriver.getHeadshotUrl(), externalDriver.getHeadshotUrl())) {
            existingDriver.setHeadshotUrl(externalDriver.getHeadshotUrl());
            isUpdated = true;
        }

        return isUpdated;
    }

    private boolean shouldUpdateField(String existingValue, String newValue) {
        // Если значение из внешнего источника равно null или пустое и в базе хранится "n/a", обновлять не нужно
        return newValue != null && !newValue.trim().isEmpty() && !(newValue.equals("n/a") && "n/a".equals(existingValue));
    }

    private boolean shouldUpdateField(Integer existingValue, Integer newValue) {
        // Если новое значение не null и отличается от старого, то обновляем
        return newValue != null && !newValue.equals(existingValue);
    }

    private void validateDriverDTO(DriverDTO driverDTO) {
        List<String> missingFields = new ArrayList<>();

        // Проверяем все обязательные поля и добавляем пустые в список
        if (driverDTO.getFirstName() == null) {
            missingFields.add("firstName");
        }
        if (driverDTO.getLastName() == null) {
            missingFields.add("lastName");
        }
        if (driverDTO.getDriverNumber() == null) {
            missingFields.add("driverNumber");
        }
        if (driverDTO.getBroadcastName() == null) {
            missingFields.add("broadcastName");
        }
        if (driverDTO.getFullName() == null) {
            missingFields.add("fullName");
        }
        if (driverDTO.getNameAcronym() == null) {
            missingFields.add("nameAcronym");
        }
        if (driverDTO.getCountryCode() == null) {
            missingFields.add("countryCode");
        }
        if (driverDTO.getHeadshotUrl() == null) {
            missingFields.add("headshotUrl");
        }

        if (!missingFields.isEmpty()) {
            throw new IllegalArgumentException("The following fields are required and cannot be null: "
                    + String.join(", ", missingFields));
        }
    }

    // TODO Убрать дублирование кода (рефлексия?)
    private void handleNullFields(DriverDTO driverDTO) {
        if (driverDTO.getFirstName() == null) {
            driverDTO.setFirstName("n/a");
        }
        if (driverDTO.getLastName() == null) {
            driverDTO.setLastName("n/a");
        }
        if (driverDTO.getDriverNumber() == null) {
            driverDTO.setDriverNumber(0);
        }
        if (driverDTO.getBroadcastName() == null) {
            driverDTO.setBroadcastName("n/a");
        }
        if (driverDTO.getFullName() == null) {
            driverDTO.setFullName("n/a");
        }
        if (driverDTO.getNameAcronym() == null) {
            driverDTO.setNameAcronym("n/a");
        }
        if (driverDTO.getCountryCode() == null) {
            driverDTO.setCountryCode("n/a");
        }
        if (driverDTO.getHeadshotUrl() == null) {
            driverDTO.setHeadshotUrl("n/a");
        }
    }

    // TODO Убрать дублирование кода (рефлексия?)
    private void handleNullFields(Driver driver) {
        if (driver.getFirstName() == null) {
            driver.setFirstName("n/a");
        }
        if (driver.getLastName() == null) {
            driver.setLastName("n/a");
        }
        if (driver.getDriverNumber() == null) {
            driver.setDriverNumber(0);
        }
        if (driver.getBroadcastName() == null) {
            driver.setBroadcastName("n/a");
        }
        if (driver.getFullName() == null) {
            driver.setFullName("n/a");
        }
        if (driver.getNameAcronym() == null) {
            driver.setNameAcronym("n/a");
        }
        if (driver.getCountryCode() == null) {
            driver.setCountryCode("n/a");
        }
        if (driver.getHeadshotUrl() == null) {
            driver.setHeadshotUrl("n/a");
        }
    }
}
