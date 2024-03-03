package com.emp.management.service.custom;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.service.SuperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleService extends SuperService<VehicleDTO, Long> {
    /**
     * FILTER VEHICLES BY ID, COLOR, MAKE, MODEL
     * @param id
     * @param color
     * @param make
     * @param model
     * @param pageable
     * @return Page of VehicleDTO
     */
    Page<VehicleDTO> getVehicles(String id, String color, String make, String model, Pageable pageable);
}
