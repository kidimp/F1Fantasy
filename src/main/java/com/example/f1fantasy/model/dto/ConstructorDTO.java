package com.example.f1fantasy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents the main information about a Formula 1 constructor (team)")
public class ConstructorDTO {

    @Schema(description = "Unique identifier of the constructor", example = "1")
    @JsonProperty("constructor_id")
    private Long id;

    @Schema(description = "Broadcast name of the constructor", example = "Mercedes")
    @JsonProperty("broadcast_name")
    private String broadcastName;

    @Schema(description = "Full name of the constructor", example = "Mercedes-AMG Petronas Formula One Team")
    @JsonProperty("full_name")
    private String fullName;

    @Schema(description = "Acronym of the constructor's name", example = "MER", maxLength = 3)
    @JsonProperty("name_acronym")
    private String nameAcronym;

    @Schema(description = "URL to the constructor's logo", example = "https://example.com/logo.jpg")
    @JsonProperty("logo_url")
    private String logoUrl;

    @Schema(description = "ISO country code of the constructor", example = "DEU", maxLength = 3)
    @JsonProperty("country_code")
    private String countryCode;

    @Schema(description = "Full name of the country where the constructor is based", example = "Germany")
    @JsonProperty("country_full_name")
    private String countryFullName;

    @Schema(description = "Base location of the constructor", example = "Brackley, United Kingdom")
    @JsonProperty("base")
    private String base;

    @Schema(description = "Team color in hex format", example = "#00D2BE")
    @JsonProperty("team_colour")
    private String teamColour;

    @Schema(description = "List of drivers for the constructor")
    private List<DriverDTO> drivers;

    @Schema(description = "List of seasons the constructor participated in")
    private List<SeasonDTO> seasons;
}
