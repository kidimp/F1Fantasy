package com.example.f1fantasy.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeasonDTO {

    /**
     * Unique identifier of the season.
     */
    @Schema(example = "1")
    private Long id;

    /**
     * Year of the season.
     */
    @Schema(example = "2024")
    private Integer year;
}
