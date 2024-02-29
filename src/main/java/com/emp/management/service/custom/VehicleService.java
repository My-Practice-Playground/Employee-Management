package com.emp.management.service.custom;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.entity.Vehicle;
import com.emp.management.service.SuperService;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface VehicleService extends SuperService<VehicleDTO,Long> {

}
