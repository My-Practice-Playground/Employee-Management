package com.emp.management.controller;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.service.custom.VehicleService;
import com.emp.management.util.payload.respond.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    /*
    Save vehicle
     */
    @PostMapping
    public ResponseEntity<StandardResponse> saveVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        log.info("Vehicle: {}", vehicleDTO);

        vehicleService.save(vehicleDTO);
        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicle Saved", null), HttpStatus.CREATED);
    }

    /*
    Get all vehicles
     */
    @GetMapping
    public ResponseEntity<StandardResponse> getAllVehicles() {
        log.info("Fetching all vehicles");

        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicles Fetched", vehicleService.findAll()), HttpStatus.OK);
    }

    /*
    Get vehicle by id
    */
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getVehicleById(@PathVariable Long id) {
        log.info("Fetching vehicle with id: {}", id);

        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicle Fetched", vehicleService.findById(id)), HttpStatus.OK);
    }

    /*
    Delete vehicle by id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteVehicle(@PathVariable Long id) {
        log.info("Deleting vehicle with id: {}", id);

        vehicleService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Vehicle Deleted", null), HttpStatus.OK);

    }


}
