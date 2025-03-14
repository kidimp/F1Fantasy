package com.example.f1fantasy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents the main information about a Formula 1 driver")
public class DriverDTO {

    @Schema(description = "Broadcast name of the driver", example = "Lewis Hamilton")
    @JsonProperty("broadcast_name")
    private String broadcastName;

    @Schema(description = "Driver's first name", example = "Lewis")
    @JsonProperty("first_name")
    private String firstName;

    @Schema(description = "Driver's last name", example = "Hamilton")
    @JsonProperty("last_name")
    private String lastName;

    @Schema(description = "Driver's full name", example = "Lewis Hamilton")
    @JsonProperty("full_name")
    private String fullName;

    @Schema(description = "Name acronym of the driver", example = "HAM", maxLength = 3)
    @JsonProperty("name_acronym")
    private String nameAcronym;

    @Schema(description = "ISO country code of the driver", example = "GBR", maxLength = 3)
    @JsonProperty("country_code")
    private String countryCode;

    @Schema(description = "Driver's number", example = "44")
    @JsonProperty("driver_number")
    private Integer driverNumber;

    @Schema(description = "URL to the driver's headshot image", example = "https://example.com/headshot.jpg")
    @JsonProperty("headshot_url")
    private String headshotUrl;
}
