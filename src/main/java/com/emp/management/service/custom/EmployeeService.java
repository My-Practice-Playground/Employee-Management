package com.emp.management.service.custom;

import com.emp.management.dto.EmployeeDTO;
import com.emp.management.dto.SupervisorDTO;
import com.emp.management.entity.Supervisor;
import com.emp.management.service.SuperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService extends SuperService<EmployeeDTO, Long> {
    List<EmployeeDTO> getAllEmployeeInEmployeeDTOType();
    Page<EmployeeDTO> getEmployeeList(String city, String email, String firstname, String lastname, Pageable pageable);
    Page<SupervisorDTO> getSupervisorList(Long id, Pageable pageable);
}
