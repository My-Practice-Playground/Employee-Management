package com.emp.management.repository;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT new com.emp.management.dto.VehicleDTO(v.id,v.model,v.manufactureDate,v.make,v.color) FROM Vehicle v")
    List<VehicleDTO> findAllVehicles();

    Optional<Vehicle> findVehicleByEmployeeId(Long id);

}
