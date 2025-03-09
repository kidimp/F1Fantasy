package com.example.f1fantasy.model.dto;

import lombok.Builder;

@Builder
public record DriverFilterDTO(Long driverId, String broadcastName, String firstName, String lastName,
                              String fullName, String nameAcronym, String countryCode, Integer driverNumber
) {
}
