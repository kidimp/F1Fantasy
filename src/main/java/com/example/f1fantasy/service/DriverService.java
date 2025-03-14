package com.example.f1fantasy.service;

import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.dto.filter.DriverFilterDTO;
import com.example.f1fantasy.model.dto.external.ExternalDriverDataDTO;

import java.util.List;

/**
 * Service for managing drivers.
 */
public interface DriverService {

    /**
     * Get a list of drivers.
     *
     * @return a list of drivers
     */
    List<DriverDTO> getDrivers(DriverFilterDTO driverFilterDTO);

    /**
     * Create a new driver manually.
     *
     * @param driverDTO - the driver's data
     * @return the created driver
     */
    DriverDTO createDriver(DriverDTO driverDTO);

    /**
     * Create a driver via the F1 API.
     *
     * @param externalDriverData - the driver's data obtained via the F1 API
     * @return the created driver
     */
    DriverDTO createDriverViaF1API(ExternalDriverDataDTO externalDriverData);

    /**
     * Update a driver's information manually.
     *
     * @param driverId  - the driver's ID
     * @param driverDTO - the updated driver's data
     * @return the updated driver data
     */
    DriverDTO updateDriver(Long driverId, DriverDTO driverDTO);

    /**
     * Update a driver's information via the F1 API.
     *
     * @param driverId           - the driver's ID
     * @param externalDriverData - the updated driver's data obtained via the F1 API
     * @return the updated driver data
     */
    DriverDTO updateDriverViaF1API(Long driverId, ExternalDriverDataDTO externalDriverData);

    /**
     * Delete a driver.
     *
     * @param driverId - the driver's ID
     */
    void deleteDriver(Long driverId);
}
