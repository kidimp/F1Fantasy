package com.example.f1fantasy.controller;

import com.example.f1fantasy.model.dto.DriverDTO;
import com.example.f1fantasy.model.dto.util.DriverCreationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
     *
     * @param driverId      - the driver's ID (optional)
     * @param broadcastName - the driver's broadcast name (optional)
     * @param countryCode   - the driver's country code (optional)
     * @param driverNumber  - the driver's number (optional)
     * @param firstName     - the driver's first name (optional)
     * @param fullName      - the driver's full name (optional)
     * @param lastName      - the driver's last name (optional)
     * @param nameAcronym   - the driver's name acronym (optional)
     * @return a list of drivers matching the query parameters
     */
    @Operation(summary = "Get information about a driver or drivers based on query parameters",
            description = """
                    If no parameters are provided, an empty response will be returned.
                    If multiple parameters are provided, it will only return drivers
                    who match all the provided parameters. If a parameter like teamName is provided,
                    multiple drivers from that team will be returned.
                    """)
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
     * @param driverDTO the driver's data
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
     * Create drivers list via the F1 API.
     *
     * @param year the year of season start
     * @return DriverCreationResponseDTO - list of created drivers and list of conflicts
     */
    @Operation(
            summary = "Create a driver via the F1 API",
            description = """
                    Метод получает список гонщиков из F1 API за определённый сезон,
                    убирает дубликаты, проверяет на существование в базе,
                    сохраняет новых пилотов и обрабатывает возможные конфликты.
                            
                    Возвращается объект DriverCreationResponseDTO, в котором:
                    - `savedDrivers` — успешно добавленные гонщики.
                    - `conflicts` — гонщики, которые не были добавлены из-за конфликта.
                    """
    )
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
    ResponseEntity<DriverCreationResponseDTO> createDriversViaF1API(@Parameter(required = true) Integer year);

    /**
     *
     * @param fullName full name
     * @param driverDTO the updated driver's data
     * @return the updated driver's data
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
    ResponseEntity<DriverDTO> updateDriverManually(@Parameter(required = true) @PathVariable String fullName,
                                                   @Valid @RequestBody DriverDTO driverDTO);

    /**
     * Update a drivers information via the F1 API.
     *
     * @param year the year of season start
     * @return the updated drivers data
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
    ResponseEntity<List<DriverDTO>> updateDriversViaF1API(@Parameter(required = true) Integer year);

    /**
     * Delete a driver.
     *
     * @param fullName - the driver's full name
     * @return 200 or 404
     */
    @Operation(summary = "Delete a driver")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver successfully deleted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema()
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
    ResponseEntity<String> deleteDriverManually(@Parameter(required = true) String fullName);
}