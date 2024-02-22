package com.emp.management.repository;

import com.emp.management.dto.EmployeeDTO;
import com.emp.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //CHECK IF EMPLOYEE EXISTS BY ID
    boolean existsById(Long id);

    //FIND EMPLOYEE BY EMAIL
    Optional<Employee> findEmployeeById(Long id);

    //CHECK IF EMAIL EXISTS
    boolean existsByEmail(String email);


    //USING JPQL QUERY TO GET ALL EMPLOYEE IN EmployeeDTO TYPE
    @Query("SELECT new com.emp.management.dto.EmployeeDTO(e.id, e.firstname, e.lastname, e.email, e.dob) FROM Employee e")
    List<EmployeeDTO> getAllEmployeeInEmployeeDTOType();
}
