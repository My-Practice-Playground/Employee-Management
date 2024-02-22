package com.emp.management.repository;

import com.emp.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsById(Long id);

    Optional<Employee> findEmployeeById(Long id);
    boolean existsByEmail(String email);
}
