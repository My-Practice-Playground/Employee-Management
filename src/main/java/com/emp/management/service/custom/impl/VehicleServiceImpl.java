package com.emp.management.service.custom.impl;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.entity.Vehicle;
import com.emp.management.repository.VehicleRepository;
import com.emp.management.service.custom.VehicleService;
import com.emp.management.util.exception.VehicleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final ModelMapper mapper;
    private final VehicleRepository vehicleRepository;

    @Transactional
    @Override
    public void save(VehicleDTO data) {
        log.info("Vehicle: {}", data);
        try {
            vehicleRepository.save(mapper.map(data, Vehicle.class));
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    @Override
    public void update(VehicleDTO data) {
        throw new RuntimeException("Sorry update method not implemented yet");
    }

    @Override
    public void delete(Long id) throws VehicleNotFoundException {
        log.info("Deleting vehicle with id: {}", id);
        try {
            if (!vehicleRepository.existsById(id)) {
                throw new VehicleNotFoundException("Vehicle not found");
            }
            vehicleRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error ", e.getCause());
            throw e;
        }
    }

    @Override
    public VehicleDTO findById(Long id) throws VehicleNotFoundException {
        log.info("Fetching vehicle with id: {}", id);

        try {
            Optional<Vehicle> byId = vehicleRepository.findById(id);
            if (!byId.isPresent()) throw new VehicleNotFoundException("Vehicle not found");

            VehicleDTO vehicleDTO = new VehicleDTO();
            vehicleDTO.setId(byId.get().getId());
            vehicleDTO.setManufactureDate(byId.get().getManufactureDate());
            vehicleDTO.setColor(byId.get().getColor());
            vehicleDTO.setMake(byId.get().getMake());
            vehicleDTO.setModel(byId.get().getModel());
            return vehicleDTO;
        } catch (Exception e) {
            log.error("Error ", e.getCause());
            throw e;
        }
    }

    @Override
    public List<VehicleDTO> findAll() {
        log.info("Fetching all vehicles");
        return vehicleRepository.findAllVehicles();
    }

    /**
     * @param id
     * @param color
     * @param make
     * @param model
     * @param pageable
     * @return Page<VehicleDTO>
     * @For fetching vehicles by make, color, model
     */
    @Override
    public Page<VehicleDTO> getVehicles(String id, String color, String make, String model, Pageable pageable) {
        log.info("Fetching vehicle with id, color, make, model: {}, {}, {}, {}", id, color, make, model);
       try{
           return vehicleRepository.getVehicleByMakeAndColor(color, make, model, pageable);
       }catch (Exception e){
           log.error("Error ",e.getCause());
           throw e;
       }
    }
}
