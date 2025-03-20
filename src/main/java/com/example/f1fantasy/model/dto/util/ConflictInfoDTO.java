package com.example.f1fantasy.model.dto.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents conflict information when creating a Formula 1 driver")
public class ConflictInfoDTO {

    @Schema(description = "Full name of the driver", example = "Lewis HAMILTON")
    @JsonProperty("full_name")
    private String fullName;

    @Schema(description = "Name acronym of the driver", example = "HAM")
    @JsonProperty("name_acronym")
    private String nameAcronym;

    @Schema(description = "Driver number", example = "44")
    @JsonProperty("driver_number")
    private Integer driverNumber;

    @Schema(description = "Reason for the conflict", example = "Duplicate driver number")
    @JsonProperty("conflict_reason")
    private String conflictReason;
}
