package com.emp.management.controller;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.service.custom.VehicleService;
import com.emp.management.util.payload.respond.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Slf4j
@RestController
@RequestMapping("api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    /**
     * SAVE VEHICLE
     * @param vehicleDTO
     * @return ResponseEntity<StandardResponse>
     */
    @PostMapping
    public ResponseEntity<StandardResponse> saveVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        log.info("Vehicle: {}", vehicleDTO);

        vehicleService.save(vehicleDTO);
        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicle Saved", null), HttpStatus.CREATED);
    }

    /**
     * GET ALL VEHICLES
     * @return ResponseEntity<StandardResponse>
     */
    @GetMapping
    public ResponseEntity<StandardResponse> getAllVehicles() {
        log.info("Fetching all vehicles");

        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicles Fetched", vehicleService.findAll()), HttpStatus.OK);
    }

    /**
     *FILTER VEHICLES BY ID, COLOR, MAKE, MODEL
     * @param id
     * @param color
     * @param make
     * @param model
     * @param size
     * @param page
     * @return Page of VehicleDTO
     */
    @GetMapping("/filter")
    public ResponseEntity<StandardResponse> getVehicleDetails(
            @RequestParam(name = "id",required = false) String id,
            @RequestParam(name = "color",required = false) String color,
            @RequestParam(name = "make",required = false) String make,
            @RequestParam(name = "model",required = false) String model,
            @RequestParam(name = "size",required = false,defaultValue = "10") Integer size,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        log.info("Fetching vehicle with id, color, make, model, date: {}, {}, {}, {}" , id, color, make, model);

        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicle Fetched",
                        vehicleService.getVehicles(id,color,make,model,Pageable.ofSize(size).withPage(page))), HttpStatus.OK);
    }

    /**
     * DELETE VEHICLE BY ID
     * @param id
     * @return ResponseEntity<StandardResponse>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteVehicle(@PathVariable Long id) {
        log.info("Deleting vehicle with id: {}", id);

        vehicleService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicle Deleted", null), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getVehicleById(@PathVariable Long id) {
        log.info("Fetching vehicle with id: {}", id);

        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicle Fetched", vehicleService.findById(id)), HttpStatus.OK);
    }

}
