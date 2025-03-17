package com.example.f1fantasy.model.dto.external;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents the main information about a Formula 1 meeting")
public class ExternalMeetingDataDTO {

    @Schema(description = "The unique identifier for the circuit where the event takes place.", example = "61")
    @JsonProperty("circuit_key")
    private int circuitKey;

    @Schema(description = "The short or common name of the circuit where the event takes place.", example = "Singapore")
    @JsonProperty("circuit_short_name")
    private String circuitShortName;

    @Schema(description = "A code that uniquely identifies the country.", example = "SGP")
    @JsonProperty("country_code")
    private String countryCode;

    @Schema(description = "The unique identifier for the country where the event takes place.", example = "157")
    @JsonProperty("country_key")
    private int countryKey;

    @Schema(description = "The full name of the country where the event takes place.", example = "Singapore")
    @JsonProperty("country_name")
    private String countryName;

    @Schema(description = "The UTC starting date and time, in ISO 8601 format.", example = "2023-09-15T09:30:00+00:00")
    @JsonProperty("date_start")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime dateStart;

    @Schema(description = "The difference in hours and minutes between local time at the location of the event and Greenwich Mean Time (GMT).", example = "08:00:00")
    @JsonProperty("gmt_offset")
    private String gmtOffset;

    @Schema(description = "The city or geographical location where the event takes place.", example = "Marina Bay")
    @JsonProperty("location")
    private String location;

    @Schema(description = "The unique identifier for the meeting. Use latest to identify the latest or current meeting.", example = "1219")
    @JsonProperty("meeting_key")
    private int meetingKey;

    @Schema(description = "The name of the meeting.", example = "Singapore Grand Prix")
    @JsonProperty("meeting_name")
    private String meetingName;

    @Schema(description = "The official name of the meeting.", example = "FORMULA 1 SINGAPORE AIRLINES SINGAPORE GRAND PRIX 2023")
    @JsonProperty("meeting_official_name")
    private String meetingOfficialName;

    @Schema(description = "The year the event takes place.", example = "2023")
    @JsonProperty("year")
    private int year;
}
