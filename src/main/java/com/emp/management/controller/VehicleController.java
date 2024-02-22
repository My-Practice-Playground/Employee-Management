package com.emp.management.controller;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.service.custom.VehicleService;
import com.emp.management.util.payload.respond.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
    private final VehicleService vehicleService;


    @PostMapping
    public ResponseEntity<StandardResponse> saveVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        logger.info("Vehicle: {}", vehicleDTO);

        return ResponseEntity.ok(new StandardResponse(200, "Vehicle Saved",vehicleDTO));
    }
}
