package com.emp.management.service.custom.impl;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.entity.Vehicle;
import com.emp.management.repository.EmployeeRepository;
import com.emp.management.repository.VehicleRepository;
import com.emp.management.service.custom.VehicleService;
import com.emp.management.util.exception.VehicleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl  implements VehicleService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);
    private final ModelMapper mapper;
    private final VehicleRepository vehicleRepository;

    @Transactional
    @Override
    public void save(VehicleDTO data) {
        logger.info("Vehicle: {}", data);

        Vehicle vehicle = mapper.map(data, Vehicle.class);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void update(VehicleDTO data) {

    }

    @Override
    public void delete(Long id)throws VehicleNotFoundException {
        logger.info("Deleting vehicle with id: {}", id);

        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return;
        }
        throw new VehicleNotFoundException("Vehicle not found");
    }

    @Override
    public VehicleDTO findById(Long id) throws VehicleNotFoundException{
        logger.info("Fetching vehicle with id: {}", id);

        Optional<Vehicle> byId = vehicleRepository.findById(id);
        if (byId.isPresent()) {
            return mapper.map(byId.get(), VehicleDTO.class);
        }
        throw new VehicleNotFoundException("Vehicle not found");
    }

    @Override
    public List<VehicleDTO> findAll() {
        logger.info("Fetching all vehicles");

        List<Vehicle> all = vehicleRepository.findAll();
        return all.stream().map(
                vehicle -> mapper.map(vehicle, VehicleDTO.class)
                ).collect(Collectors.toList());
    }
}
