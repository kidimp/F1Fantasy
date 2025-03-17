package com.example.f1fantasy.service.impl;

import com.example.f1fantasy.client.OpenF1Client;
import com.example.f1fantasy.exception.DataNotExistsException;
import com.example.f1fantasy.exception.DataNotFoundException;
import com.example.f1fantasy.exception.DriverAlreadyExistsException;
import com.example.f1fantasy.mapper.DriverMapper;
import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.dto.external.ExternalDriverDataDTO;
import com.example.f1fantasy.model.dto.external.ExternalMeetingDataDTO;
import com.example.f1fantasy.model.dto.external.ExternalSessionDataDTO;
import com.example.f1fantasy.model.dto.filter.DriverFilterDTO;
import com.example.f1fantasy.model.entity.Driver;
import com.example.f1fantasy.model.enums.SessionTypeEnum;
import com.example.f1fantasy.repository.DriverRepository;
import com.example.f1fantasy.repository.specification.DriverSpecification;
import com.example.f1fantasy.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        if (driverRepository.existsByBroadcastNameAndFirstNameAndLastNameAndFullName(
                driverDTO.getBroadcastName(),
                driverDTO.getFirstName(),
                driverDTO.getLastName(),
                driverDTO.getFullName())) {
            throw new DriverAlreadyExistsException("A driver with the same name already exists: " + driverDTO.getFullName());
        }

        Driver driverEntity = driverMapper.toEntity(driverDTO);

        Driver savedDriver = driverRepository.save(driverEntity);

        return driverMapper.toDTO(savedDriver);
    }

    @Override
    public List<DriverDTO> createDriversViaF1API(Integer year) {
        // Получаем список всех гонок за конкретный год
        List<ExternalMeetingDataDTO> meetings = openF1Client.getMeetingsByYear(year);
        if (meetings.isEmpty()) {
            throw new DataNotFoundException("No meetings found for " + year + ".");
        }

        // Берем meeting_key последнего события
        int latestMeetingKey = meetings.get(meetings.size() - 1).getMeetingKey();

        // Получаем список сессий по этому meeting_key
        List<ExternalSessionDataDTO> sessions = openF1Client.getSessionsByMeetingKey(latestMeetingKey);

        // Ищем сессию с session_type == "Race"
        Optional<Integer> raceSessionKey = sessions.stream()
                .filter(session -> SessionTypeEnum.RACE.getValue().equalsIgnoreCase(session.getSessionType()))
                .map(ExternalSessionDataDTO::getSessionKey)
                .findFirst();

        if (raceSessionKey.isEmpty()) {
            throw new DataNotFoundException("No race session found for meeting_key " + latestMeetingKey);
        }

        // Получаем список гонщиков по найденному session_key
        List<ExternalDriverDataDTO> drivers = openF1Client.getDriversBySessionKey(raceSessionKey.get());

        // Убираем дубликаты по full_name, начиная с конца списка
        // (т.к. в конце списка будут пилоты, которые участвовали в гонке)
        Map<String, ExternalDriverDataDTO> uniqueDrivers = new LinkedHashMap<>();
        for (int i = drivers.size() - 1; i >= 0; i--) {
            ExternalDriverDataDTO driver = drivers.get(i);
            uniqueDrivers.putIfAbsent(driver.getFullName(), driver);
        }

        // Маппим в DriverDTO
        List<DriverDTO> driverDTOs = driverMapper.toDriverDTOList(new ArrayList<>(uniqueDrivers.values()));

        // Проверяем поля на null и заменяем на n/a если пустые
        driverDTOs.forEach(this::handleNullFields);

        List<Driver> driverEntities = driverDTOs.stream()                   // Преобразуем список driverDTOs в stream
                .filter(driverDTO -> !driverRepository.existsByBroadcastNameAndFullName(
                        driverDTO.getBroadcastName(),                       // Отфильтровываем дубликаты — если такой
                        driverDTO.getFullName()                             // гонщик уже есть в БД, то пропускаем его
                ))
                .map(driverMapper::toEntity)                                // Маппим оставшихся гонщиков в Driver
                .toList();                                                  // Если список не пуст, сохраняем в БД

        if (!driverEntities.isEmpty()) {
            driverRepository.saveAll(driverEntities);
        }

        return driverDTOs;
    }

    @Override
    public DriverDTO updateDriver(Long driverId, DriverDTO driverDTO) {
        // TODO Сделать обновление не по id, по другому полю
        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DataNotExistsException("Driver with id " + driverId + " not found"));

        // Используем маппер для обновления только переданных полей
        driverMapper.updateEntityFromDTO(driverDTO, existingDriver);

        Driver updatedDriver = driverRepository.save(existingDriver);

        return driverMapper.toDTO(updatedDriver);
    }

    @Override
    public DriverDTO updateDriverViaF1API(Long driverId, ExternalDriverDataDTO externalDriverData) {
        return null;
    }

    @Override
    public void deleteDriver(Long driverId) {
        // TODO сделать ответ, чтобы понимать - успешное удаление или нет
        // TODO сделать удаление не по id, по другому полю
        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DataNotExistsException("Driver with id " + driverId + " not found"));

        driverRepository.delete(existingDriver);
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
}
