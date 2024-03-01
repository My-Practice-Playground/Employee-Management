package com.emp.management.service.custom;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.entity.Vehicle;
import com.emp.management.service.SuperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

public interface VehicleService extends SuperService<VehicleDTO,Long> {

    Page<VehicleDTO> getVehicles(String id, String color, String make, String model, Pageable pageable);
}
