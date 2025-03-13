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
@Schema(description = "Represents the main information about a Formula 1 season")
public class SeasonDTO {

    @Schema(description = "Unique identifier of the season", example = "2024")
    @JsonProperty("season_id")
    private Long id;

    @Schema(description = "Year of the season", example = "2024")
    private Integer year;

    @Schema(description = "List of Grand Prix for the season")
    private List<GrandPrixDTO> grandPrixList;

    @Schema(description = "List of constructors for the season")
    private List<ConstructorDTO> constructors;
}
