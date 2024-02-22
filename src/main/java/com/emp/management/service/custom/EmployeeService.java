package com.emp.management.service.custom;

import com.emp.management.dto.EmployeeDTO;
import com.emp.management.service.SuperService;

import java.util.List;

public interface EmployeeService extends SuperService<EmployeeDTO, Long> {
    List<EmployeeDTO> getAllEmployeeInEmployeeDTOType();
}
