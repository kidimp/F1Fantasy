package com.example.f1fantasy.model.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for Driver entity.
 * This class represents the main information about a Formula 1 driver.
 * The data for this DTO is fetched from the F1 API.
 */

public class ExternalDriverDataDTO {

    @Schema(description = "Broadcast name of the driver", example = "Lewis Hamilton")
    @JsonProperty("broadcast_name")
    private String broadcastName;

    @Schema(description = "ISO country code of the driver", example = "GBR", maxLength = 3)
    @JsonProperty("country_code")
    private String countryCode;

    @Schema(description = "Driver's number", example = "44")
    @JsonProperty("driver_number")
    private Integer driverNumber;

    @Schema(description = "Driver's first name", example = "Lewis")
    @JsonProperty("first_name")
    private String firstName;

    @Schema(description = "Driver's full name", example = "Lewis Hamilton")
    @JsonProperty("full_name")
    private String fullName;

    @Schema(description = "URL to the driver's headshot image", example = "https://example.com/headshot.jpg")
    @JsonProperty("headshot_url")
    private String headshotUrl;

    @Schema(description = "Driver's last name", example = "Hamilton")
    @JsonProperty("last_name")
    private String lastName;

    @Schema(description = "Meeting key for the driver's event", example = "101")
    @JsonProperty("meeting_key")
    private Integer meetingKey;

    @Schema(description = "Name acronym of the driver", example = "HAM", maxLength = 3)
    @JsonProperty("name_acronym")
    private String nameAcronym;

    @Schema(description = "Session key for the driver's event", example = "201")
    @JsonProperty("session_key")
    private Integer sessionKey;

    @Schema(description = "Color associated with the driver's team", example = "#FFFFFF")
    @JsonProperty("team_colour")
    private String teamColour;

    @Schema(description = "Name of the driver's team", example = "Mercedes")
    @JsonProperty("team_name")
    private String teamName;
}
