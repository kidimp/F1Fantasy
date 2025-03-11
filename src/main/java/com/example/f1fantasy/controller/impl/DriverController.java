//package com.example.f1fantasy.controller.impl;
//
//import com.example.f1fantasy.controller.DriverControllerAPI;
//import com.example.f1fantasy.model.dto.DriverDTO;
//import com.example.f1fantasy.model.dto.DriverFilterDTO;
//import com.example.f1fantasy.model.dto.ExternalDriverDataDTO;
//import com.example.f1fantasy.service.DriverService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/f1fantasy/driver")
//public class DriverController implements DriverControllerAPI {
//
//    private final DriverService driverService;
//
//    @Override
//    @GetMapping("")
//    public ResponseEntity<List<DriverDTO>> getDrivers(Long driverId, String broadcastName, String firstName,
//                                                      String lastName, String fullName, String nameAcronym,
//                                                      String countryCode, Integer driverNumber) {
//
//        DriverFilterDTO driverFilterDTO = DriverFilterDTO.builder()
//                .driverId(driverId)
//                .broadcastName(broadcastName)
//                .firstName(firstName)
//                .lastName(lastName)
//                .fullName(fullName)
//                .nameAcronym(nameAcronym)
//                .countryCode(countryCode)
//                .driverNumber(driverNumber)
//                .build();
//
//        return ResponseEntity.ok(driverService.getDrivers(driverFilterDTO));
//    }
//
//    @Override
//    @PostMapping("")
//    public ResponseEntity<DriverDTO> createDriverManually(@Valid @RequestBody DriverDTO driverDTO) {
//        DriverDTO createdDriver = driverService.createDriver(driverDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdDriver);
//    }
//
//    @Override
//    @PostMapping("/via-f1-api")
//    public ResponseEntity<DriverDTO> createDriverViaF1API(@Valid @RequestBody ExternalDriverDataDTO externalDriverData) {
//        DriverDTO createdDriver = driverService.createDriverViaF1API(externalDriverData);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdDriver);
//    }
//
//    @Override
//    @PutMapping("/{driverId}")
//    public ResponseEntity<DriverDTO> updateDriverManually(@PathVariable Long driverId,
//                                                          @Valid @RequestBody DriverDTO driverDTO) {
//        DriverDTO updatedDriver = driverService.updateDriver(driverId, driverDTO);
//        return ResponseEntity.ok(updatedDriver);
//    }
//
//    @Override
//    @PutMapping("/{driverId}/via-f1-api")
//    public ResponseEntity<DriverDTO> updateDriverViaF1API(@PathVariable Long driverId,
//                                                          @Valid @RequestBody ExternalDriverDataDTO externalDriverData) {
//        DriverDTO updatedDriver = driverService.updateDriverViaF1API(driverId, externalDriverData);
//        return ResponseEntity.ok(updatedDriver);
//    }
//
//    @Override
//    @DeleteMapping("/{driverId}")
//    public ResponseEntity<Void> deleteDriverManually(@PathVariable Long driverId) {
//        driverService.deleteDriver(driverId);
//        return ResponseEntity.noContent().build();
//    }
//}