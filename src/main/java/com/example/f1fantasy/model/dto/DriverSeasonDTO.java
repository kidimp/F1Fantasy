package com.example.f1fantasy.model.dto;

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
public class DriverSeasonDTO {

    /**
     * ID сезона.
     */
    @Schema(description = "Season year", example = "2024")
    @JsonProperty("season_year")
    private Integer seasonYear;

    /**
     * Информация о пилоте.
     */
    @Schema(description = "Driver details")
    @JsonProperty("driver")
    private DriverDTO driver;

    /**
     * Информация о команде, за которую выступал пилот.
     */
    @Schema(description = "Constructor details")
    @JsonProperty("constructor")
    private ConstructorDTO constructor;

    /**
     * Количество гонок, проведённых за эту команду в данном сезоне.
     */
    @Schema(description = "Number of races driven for this constructor in the season", example = "12")
    @JsonProperty("races_count")
    private Integer racesCount;
}
