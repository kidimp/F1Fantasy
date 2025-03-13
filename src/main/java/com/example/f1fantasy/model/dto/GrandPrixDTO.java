package com.example.f1fantasy.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents the information about a Formula 1 Grand Prix")
public class GrandPrixDTO {

    @Schema(description = "Unique identifier of the Grand Prix", example = "101")
    private Long id;

    @Schema(description = "Circuit related to the Grand Prix", example = "Silverstone Circuit")
    private CircuitDTO circuit;

    @Schema(description = "Season information for the Grand Prix", example = "2025")
    private Integer seasonYear;

    @Schema(description = "Start date and time of the Grand Prix", example = "2025-07-12T14:00:00")
    private LocalDateTime dateStart;

    @Schema(description = "GMT offset for the event", example = "GMT+1")
    private String gmtOffset;

    @Schema(description = "Meeting key", example = "1")
    private Integer meetingKey;

    @Schema(description = "Name of the meeting", example = "British Grand Prix")
    private String meetingName;

    @Schema(description = "Official name of the meeting", example = "Formula 1 Pirelli Gran Premio Britannia 2025")
    private String meetingOfficialName;
}