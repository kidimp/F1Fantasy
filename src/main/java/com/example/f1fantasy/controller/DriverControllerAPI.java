package com.example.f1fantasy.controller;

import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.dto.external.ExternalDriverDataDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller for managing drivers.
 * This controller provides methods for CRUD operations related to drivers.
 */
@Tag(name = "Driver Controller", description = "Controller for managing drivers")
public interface DriverControllerAPI {

    /**
     * Get information about a driver or drivers based on the provided query parameters.
     * If no parameters are provided, an empty response will be returned.
     *
     * If multiple parameters are provided, it will only return drivers who match all the provided parameters.
     * If a parameter like teamName is provided, multiple drivers from that team will be returned.
     *
     * @param driverId - the driver's ID (optional)
     * @param broadcastName - the driver's broadcast name (optional)
     * @param countryCode - the driver's country code (optional)
     * @param driverNumber - the driver's number (optional)
     * @param firstName - the driver's first name (optional)
     * @param fullName - the driver's full name (optional)
     * @param lastName - the driver's last name (optional)
     * @param nameAcronym - the driver's name acronym (optional)
     * @param teamName - the driver's team name (optional)
     * @return a list of drivers matching the query parameters
     */
    @Operation(summary = "Get information about a driver or drivers based on query parameters")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Data successfully retrieved",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DriverDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No drivers found matching the given parameters",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    ResponseEntity<List<DriverDTO>> getDrivers(
            @Parameter(description = "Driver's ID") @RequestParam(required = false) Long driverId,
            @Parameter(description = "Driver's broadcast name") @RequestParam(required = false) String broadcastName,
            @Parameter(description = "Driver's first name") @RequestParam(required = false) String firstName,
            @Parameter(description = "Driver's last name") @RequestParam(required = false) String lastName,
            @Parameter(description = "Driver's full name") @RequestParam(required = false) String fullName,
            @Parameter(description = "Driver's name acronym") @RequestParam(required = false) String nameAcronym,
            @Parameter(description = "Driver's country code") @RequestParam(required = false) String countryCode,
            @Parameter(description = "Driver's number") @RequestParam(required = false) Integer driverNumber
    );

    /**
     * Create a new driver manually.
     *
     * @param driverDTO - the driver's data
     * @return the created driver
     */
    @Operation(summary = "Create a new driver manually")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Driver successfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DriverDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    ResponseEntity<DriverDTO> createDriverManually(@Parameter(required = true) DriverDTO driverDTO);

    /**
     * Create a driver via the F1 API.
     *
     * @param externalDriverData - the driver's data obtained via the F1 API
     * @return the created driver
     */
    @Operation(summary = "Create a driver via the F1 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Driver successfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DriverDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    ResponseEntity<DriverDTO> createDriverViaF1API(@Parameter(required = true) ExternalDriverDataDTO externalDriverData);

    /**
     * Update a driver's information manually.
     *
     * @param driverId  - the driver's ID
     * @param driverDTO - the updated driver's data
     * @return the updated driver data
     */
    @Operation(summary = "Update a driver's information manually")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver successfully updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DriverDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Driver not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    ResponseEntity<DriverDTO> updateDriverManually(@Parameter(required = true) Long driverId,
                                                   @Parameter(required = true) DriverDTO driverDTO);

    /**
     * Update a driver's information via the F1 API.
     *
     * @param driverId           - the driver's ID
     * @param externalDriverData - the updated driver's data obtained via the F1 API
     * @return the updated driver data
     */
    @Operation(summary = "Update a driver's information via the F1 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver successfully updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DriverDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Driver not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    ResponseEntity<DriverDTO> updateDriverViaF1API(@Parameter(required = true) Long driverId,
                                                   @Parameter(required = true) ExternalDriverDataDTO externalDriverData);

    /**
     * Delete a driver.
     *
     * @param driverId - the driver's ID
     */
    @Operation(summary = "Delete a driver")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver successfully deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Void.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Driver not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    ResponseEntity<Void> deleteDriverManually(@Parameter(required = true) Long driverId);
}