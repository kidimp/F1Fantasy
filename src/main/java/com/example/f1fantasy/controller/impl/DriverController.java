package com.example.f1fantasy.controller.impl;

import com.example.f1fantasy.controller.DriverControllerAPI;
import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.dto.filter.DriverFilterDTO;
import com.example.f1fantasy.model.dto.util.DriverCreationResponseDTO;
import com.example.f1fantasy.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/f1fantasy/driver")
public class DriverController implements DriverControllerAPI {

    private final DriverService driverService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<DriverDTO>> getDrivers(Long driverId, String broadcastName, String firstName,
                                                      String lastName, String fullName, String nameAcronym,
                                                      String countryCode, Integer driverNumber) {

        DriverFilterDTO driverFilterDTO = DriverFilterDTO.builder()
                .driverId(driverId)
                .broadcastName(broadcastName)
                .firstName(firstName)
                .lastName(lastName)
                .fullName(fullName)
                .nameAcronym(nameAcronym)
                .countryCode(countryCode)
                .driverNumber(driverNumber)
                .build();

        return ResponseEntity.ok(driverService.getDrivers(driverFilterDTO));
    }

    @Override
    @PostMapping("")
    public ResponseEntity<DriverDTO> createDriverManually(@Valid @RequestBody DriverDTO driverDTO) {
        DriverDTO createdDriver = driverService.createDriver(driverDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDriver);
    }

    @Override
    @PostMapping("/list/via-f1-api")
    public ResponseEntity<DriverCreationResponseDTO> createDriversViaF1API(Integer year) {
        DriverCreationResponseDTO createdDrivers = driverService.createDriversViaF1API(year);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDrivers);
    }

    @Override
    @PutMapping("/{fullName}")
    public ResponseEntity<DriverDTO> updateDriverManually(@PathVariable String fullName,
                                                          @Valid @RequestBody DriverDTO driverDTO) {
        DriverDTO updatedDriver = driverService.updateDriver(fullName, driverDTO);
        return ResponseEntity.ok(updatedDriver);
    }

    @Override
    @PutMapping("/list/via-f1-api")
    public ResponseEntity<List<DriverDTO>> updateDriversViaF1API(Integer year) {
        List<DriverDTO> updatedDrivers = driverService.updateDriversViaF1API(year);
        return ResponseEntity.ok(updatedDrivers);
    }

    @Override
    @DeleteMapping("/{fullName}")
    public ResponseEntity<String> deleteDriverManually(@PathVariable String fullName) {
        driverService.deleteDriver(fullName);
        return ResponseEntity.ok("Driver " + fullName + " deleted successfully");
    }
}