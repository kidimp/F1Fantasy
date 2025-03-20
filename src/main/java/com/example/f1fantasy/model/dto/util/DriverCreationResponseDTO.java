package com.example.f1fantasy.model.dto.util;

import com.example.f1fantasy.model.dto.DriverDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response DTO for driver creation process, containing successfully saved drivers and conflicts")
public class DriverCreationResponseDTO {

    @Schema(description = "List of successfully saved drivers")
    @JsonProperty("saved_drivers")
    private List<DriverDTO> savedDrivers;

    @Schema(description = "List of conflicts encountered during driver creation")
    @JsonProperty("conflicts")
    private List<ConflictInfoDTO> conflicts;
}
