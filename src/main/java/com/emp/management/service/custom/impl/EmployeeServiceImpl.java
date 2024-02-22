package com.emp.management.service.custom.impl;

import com.emp.management.dto.EmployeeDTO;
import com.emp.management.entity.Employee;
import com.emp.management.repository.EmployeeRepository;
import com.emp.management.service.custom.EmployeeService;
import com.emp.management.util.exception.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper mapper;
    private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());
    private final EmployeeRepository employeeRepository;

    /*
        Performs Employee Save Operation
     */
    @Transactional
    @Override
    public void save(EmployeeDTO data) {
        logger.info("Employee: " + data);
        if(!employeeRepository.existsByEmail(data.getEmail())){
            employeeRepository.save(mapper.map(data, Employee.class));
            return;
        }
        throw new RuntimeException("Employee already exists!");
    }


    @Transactional
    @Override
    public void update(EmployeeDTO data) {

    }

    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("Deleting employee with id: " + id);
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDTO findById(Long id) throws EmployeeNotFoundException{
        logger.info("Fetching employee with id: " + id);
        Optional<Employee> employeeById = employeeRepository.findEmployeeById(id);
        if (employeeById.isPresent()) {
            return mapper.map(employeeById.get(), EmployeeDTO.class);
        }
        throw new EmployeeNotFoundException("Employee not found");
    }

    @Override
    public List<EmployeeDTO> findAll() {
        logger.info("Fetching all employees");
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(
                employee -> mapper.map(employee, EmployeeDTO.class)).toList();
    }
}
