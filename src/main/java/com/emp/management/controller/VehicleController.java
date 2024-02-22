package com.emp.management.controller;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.service.custom.VehicleService;
import com.emp.management.util.payload.respond.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
    private final VehicleService vehicleService;

    /*
    Save vehicle
     */
    @PostMapping
    public ResponseEntity<StandardResponse> saveVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        logger.info("Vehicle: {}", vehicleDTO);

        vehicleService.save(vehicleDTO);
        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicle Saved", null), HttpStatus.CREATED);
    }

    /*
    Get all vehicles
     */
    @GetMapping
    public ResponseEntity<StandardResponse> getAllVehicles() {
        logger.info("Fetching all vehicles");

        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicles Fetched", vehicleService.findAll()), HttpStatus.OK);
    }

    /*
    Get vehicle by id
    */
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getVehicleById(@PathVariable Long id) {
        logger.info("Fetching vehicle with id: {}", id);

        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicle Fetched", vehicleService.findById(id)), HttpStatus.OK);
    }

    /*
    Delete vehicle by id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteVehicle(@PathVariable Long id) {
        logger.info("Deleting vehicle with id: {}", id);

        vehicleService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicle Deleted", null), HttpStatus.OK);

    }


}
