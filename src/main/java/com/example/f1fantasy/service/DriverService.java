package com.example.f1fantasy.service;

import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.dto.filter.DriverFilterDTO;
import com.example.f1fantasy.model.dto.util.DriverCreationResponseDTO;

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
     * Create drivers list via the F1 API.
     *
     * @param year the year of season start
     * @return the created list of drivers
     */
    DriverCreationResponseDTO createDriversViaF1API(Integer year);

    /**
     * Update a driver's information manually.
     *
     * @param fullName  - the driver's full name
     * @param driverDTO - the updated driver's data
     * @return the updated driver data
     */
    DriverDTO updateDriver(String fullName, DriverDTO driverDTO);

    /**
     * Update a driver's information via the F1 API.
     *
     * @param year the year of season start
     * @return the updated driver data
     */
    List<DriverDTO> updateDriversViaF1API(Integer year);

    /**
     * Delete a driver.
     *
     * @param fullName - the driver's full name
     */
    void deleteDriver(String fullName);
}
