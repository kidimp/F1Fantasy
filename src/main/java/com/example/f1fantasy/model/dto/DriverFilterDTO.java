package com.example.f1fantasy.model.dto;

import lombok.Builder;

@Builder
public record DriverFilterDTO(Long driverId, String broadcastName, String countryCode,
                              Integer driverNumber, String firstName, String fullName,
                              String lastName, String nameAcronym, String teamName
) {
}
