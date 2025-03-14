package com.example.f1fantasy.service.impl;

import com.example.f1fantasy.exception.DataNotExistsException;
import com.example.f1fantasy.exception.DriverAlreadyExistsException;
import com.example.f1fantasy.mapper.DriverMapper;
import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.dto.external.ExternalDriverDataDTO;
import com.example.f1fantasy.model.dto.filter.DriverFilterDTO;
import com.example.f1fantasy.model.entity.Driver;
import com.example.f1fantasy.repository.DriverRepository;
import com.example.f1fantasy.repository.specification.DriverSpecification;
import com.example.f1fantasy.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

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
    public DriverDTO createDriverViaF1API(ExternalDriverDataDTO externalDriverData) {
        return null;
    }

    @Override
    public DriverDTO updateDriver(Long driverId, DriverDTO driverDTO) {
        //TODO Сделать обновление не по id, по другому полю
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
}
