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
     * @param model
     * @param pageable
     * @return Page of Vehicles
     * @For Filtering Vehicles from make and color
     */
    @Query("SELECT new com.emp.management.dto.VehicleDTO(v.id, v.make, v.manufactureDate, v.model, v.color) " +
            "FROM Vehicle v " +
            "WHERE " +
            "(:make IS NULL OR LOWER(v.make) LIKE LOWER(concat('%', :make, '%'))) " +
            "AND (:color IS NULL OR LOWER(v.color) LIKE LOWER(concat('%', :color, '%'))) " +
            "AND (:model IS NULL OR LOWER(v.model) LIKE LOWER(concat('%', :model, '%')))")
    Page<VehicleDTO> getVehicleByMakeAndColor(String color, String make, String model, Pageable pageable);
}
