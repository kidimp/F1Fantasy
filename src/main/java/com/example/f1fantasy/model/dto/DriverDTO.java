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
    @JsonProperty("id")
    private Long id;

    /**
     * Broadcast name of the driver.
     */
    @Schema(description = "Broadcast name of the driver", example = "Lewis Hamilton")
    @JsonProperty("broadcast_name")
    private String broadcastName;

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
     * Driver's first name.
     */
    @Schema(description = "Driver's first name", example = "Lewis")
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Driver's full name.
     */
    @Schema(description = "Driver's full name", example = "Lewis Hamilton")
    @JsonProperty("full_name")
    private String fullName;

    /**
     * URL to the driver's headshot image.
     */
    @Schema(description = "URL to the driver's headshot image", example = "https://example.com/headshot.jpg")
    @JsonProperty("headshot_url")
    private String headshotUrl;

    /**
     * Driver's last name.
     */
    @Schema(description = "Driver's last name", example = "Hamilton")
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Meeting key for the driver's event.
     */
    @Schema(description = "Meeting key for the driver's event", example = "101")
    @JsonProperty("meeting_key")
    private Integer meetingKey;

    /**
     * Acronym of the driver's name (3 characters).
     */
    @Schema(description = "Name acronym of the driver", example = "HAM", maxLength = 3)
    @JsonProperty("name_acronym")
    private String nameAcronym;

    /**
     * Session key for the driver's event.
     */
    @Schema(description = "Session key for the driver's event", example = "201")
    @JsonProperty("session_key")
    private Integer sessionKey;

    /**
     * Color associated with the driver's team in hexadecimal format.
     */
    @Schema(description = "Color associated with the driver's team", example = "#FFFFFF")
    @JsonProperty("team_colour")
    private String teamColour;

    /**
     * Name of the driver's team.
     */
    @Schema(description = "Name of the driver's team", example = "Mercedes")
    @JsonProperty("team_name")
    private String teamName;

    /**
     * Cost of the driver in the game.
     */
    @Schema(description = "Cost of the driver in the game", example = "12.5")
    @JsonProperty("cost")
    private double cost;
}
