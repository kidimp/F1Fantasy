package com.example.f1fantasy.service.impl;

import com.example.f1fantasy.mapper.DriverMapper;
import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.dto.DriverFilterDTO;
import com.example.f1fantasy.model.dto.ExternalDriverDataDTO;
import com.example.f1fantasy.model.entity.Driver;
import com.example.f1fantasy.repository.DriverRepository;
import com.example.f1fantasy.repository.specification.DriverSpecification;
import com.example.f1fantasy.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return null;
    }

    @Override
    public DriverDTO createDriverViaF1API(ExternalDriverDataDTO externalDriverData) {
        return null;
    }

    @Override
    public DriverDTO updateDriver(Long driverId, DriverDTO driverDTO) {
        return null;
    }

    @Override
    public DriverDTO updateDriverViaF1API(Long driverId, ExternalDriverDataDTO externalDriverData) {
        return null;
    }

    @Override
    public void deleteDriver(Long driverId) {

    }
}
