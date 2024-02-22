package com.emp.management.service.custom.impl;

import com.emp.management.entity.Vehicle;
import com.emp.management.repository.EmployeeRepository;
import com.emp.management.repository.VehicleRepository;
import com.emp.management.service.custom.VehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl  implements VehicleService {
    private final ModelMapper mapper;
    private final VehicleRepository vehicleRepository;
    @Override
    public void save(Vehicle data) {

    }

    @Override
    public void update(Vehicle data) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Vehicle findById(Long id) {
        return null;
    }

    @Override
    public List<Vehicle> findAll() {
        return null;
    }
}
