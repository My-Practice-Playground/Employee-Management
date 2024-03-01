package com.emp.management.repository;

import com.emp.management.dto.VehicleDTO;
import com.emp.management.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT new com.emp.management.dto.VehicleDTO(v.id,v.model,v.manufactureDate,v.make,v.color) FROM Vehicle v")
    List<VehicleDTO> findAllVehicles();

    /**
     *
     * @param id
     * @return Vehicle Entity
     * @for Filter vehicle by Employee Id
     */
    Optional<Vehicle> findVehicleByEmployeeId(Long id);

    /**
     * @param make
     * @param color
     * @param pageable
     * @return Page of Vehicles
     * @For Filtering Vehicles from make and color
     */
    @Query(value = "SELECT v.model,v.color,v.manufactureDate,v.make,v.id FROM Vehicle v WHERE " + "(:make IS NULL OR v.make = :make) AND " + "(:color IS null  or v.color like %:color%)")
    Page<Vehicle> getVehicleByMakeAndColor(String make, String color, Pageable pageable);

}
