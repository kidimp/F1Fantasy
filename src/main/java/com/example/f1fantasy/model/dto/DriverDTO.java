package com.example.f1fantasy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for Driver entity.
 * This class represents the main information about a Formula 1 driver.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

    /**
     * Unique identifier of the driver.
     */
    @Schema(description = "Unique identifier of the driver", example = "1")
    private Long id;

    /**
     * Broadcast name of the driver.
     */
    @Schema(description = "Broadcast name of the driver", example = "Lewis Hamilton")
    @JsonProperty("broadcast_name")
    private String broadcastName;

    /**
     * Driver's first name.
     */
    @Schema(description = "Driver's first name", example = "Lewis")
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Driver's last name.
     */
    @Schema(description = "Driver's last name", example = "Hamilton")
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Driver's full name.
     */
    @Schema(description = "Driver's full name", example = "Lewis Hamilton")
    @JsonProperty("full_name")
    private String fullName;

    /**
     * Acronym of the driver's name (3 characters).
     */
    @Schema(description = "Name acronym of the driver", example = "HAM", maxLength = 3)
    @JsonProperty("name_acronym")
    private String nameAcronym;

    /**
     * ISO country code of the driver (3 characters).
     */
    @Schema(description = "ISO country code of the driver", example = "GBR", maxLength = 3)
    @JsonProperty("country_code")
    private String countryCode;

    /**
     * Driver's number.
     */
    @Schema(description = "Driver's number", example = "44")
    @JsonProperty("driver_number")
    private Integer driverNumber;

    /**
     * URL to the driver's headshot image.
     */
    @Schema(description = "URL to the driver's headshot image", example = "https://example.com/headshot.jpg")
    @JsonProperty("headshot_url")
    private String headshotUrl;
}
