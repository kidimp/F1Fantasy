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
@Schema(description = "Represents the main information about a Formula 1 circuit")
public class CircuitDTO {

    @Schema(description = "Unique identifier of the circuit", example = "1")
    private Integer circuitKey;

    @Schema(description = "Short name of the circuit", example = "Silverstone")
    private String circuitShortName;

    @Schema(description = "Country code where the circuit is located (ISO 3166-1 alpha-3)", example = "GBR")
    private String countryCode;

    @Schema(description = "Unique identifier of the country in the database", example = "44")
    private Integer countryKey;

    @Schema(description = "Full name of the country where the circuit is located", example = "United Kingdom")
    private String countryName;

    @Schema(description = "City or location of the circuit", example = "Silverstone")
    private String location;
}
