package com.example.f1fantasy.model.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents the main information about a Formula 1 session. A session refers to a distinct " +
        "period of track activity during a Grand Prix or testing weekend (practice, qualifying, sprint, race, ...).")
public class ExternalSessionDataDTO {

    @JsonProperty("session_key")
    @Schema(description = "Unique identifier for the session", example = "9693")
    private int sessionKey;

    @JsonProperty("session_name")
    @Schema(description = "Name of the session (e.g., 'Race', 'Qualifying')", example = "Race")
    private String sessionName;

    @JsonProperty("date_start")
    @Schema(description = "Start date and time of the session (ISO format)", example = "2025-03-16T04:00:00+00:00")
    private String dateStart;

    @JsonProperty("date_end")
    @Schema(description = "End date and time of the session (ISO format)", example = "2025-03-16T06:00:00+00:00")
    private String dateEnd;

    @JsonProperty("gmt_offset")
    @Schema(description = "GMT offset for the session", example = "11:00:00")
    private String gmtOffset;

    @JsonProperty("session_type")
    @Schema(description = "Type of session (e.g., 'Practice', 'Qualifying', 'Race')", example = "Race")
    private String sessionType;

    @JsonProperty("meeting_key")
    @Schema(description = "Meeting key associated with this session", example = "1254")
    private int meetingKey;

    @JsonProperty("location")
    @Schema(description = "Location of the session", example = "Melbourne")
    private String location;

    @JsonProperty("country_key")
    @Schema(description = "Unique identifier for the country", example = "5")
    private int countryKey;

    @JsonProperty("country_code")
    @Schema(description = "ISO country code", example = "AUS")
    private String countryCode;

    @JsonProperty("country_name")
    @Schema(description = "Full name of the country", example = "Australia")
    private String countryName;

    @JsonProperty("circuit_key")
    @Schema(description = "Unique identifier for the circuit", example = "10")
    private int circuitKey;

    @JsonProperty("circuit_short_name")
    @Schema(description = "Short name of the circuit", example = "Melbourne")
    private String circuitShortName;

    @JsonProperty("year")
    @Schema(description = "Year in which the session takes place", example = "2025")
    private int year;
}
