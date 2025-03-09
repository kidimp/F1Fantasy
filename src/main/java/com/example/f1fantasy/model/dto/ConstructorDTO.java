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
public class ConstructorDTO {

    /**
     * Unique identifier of the constructor.
     */
    @Schema(example = "1")
    private Long id;

    /**
     * Broadcast name of the constructor.
     */
    @Schema(example = "Mercedes")
    private String broadcastName;

    /**
     * Full name of the constructor.
     */
    @Schema(example = "Mercedes-AMG Petronas Formula One Team")
    private String fullName;

    /**
     * Acronym of the constructor's name (e.g., "MER").
     */
    @Schema(example = "MER", maxLength = 3)
    private String nameAcronym;

    /**
     * URL to the constructor's logo.
     */
    @Schema(example = "https://example.com/logo.jpg")
    private String logoUrl;

    /**
     * ISO country code of the constructor.
     */
    @Schema(example = "DEU", maxLength = 3)
    private String countryCode;

    /**
     * Full name of the country where the constructor is based.
     */
    @Schema(example = "Germany")
    private String countryFullName;

    /**
     * Base location of the constructor.
     */
    @Schema(example = "Brackley, United Kingdom")
    private String base;

    /**
     * Team color in hex format.
     */
    @Schema(example = "#00D2BE")
    private String teamColour;
}

