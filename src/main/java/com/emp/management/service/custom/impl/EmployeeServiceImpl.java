package com.emp.management.service.custom.impl;

import com.emp.management.dto.EmployeeDTO;
import com.emp.management.entity.Employee;
import com.emp.management.entity.Vehicle;
import com.emp.management.repository.EmployeeRepository;
import com.emp.management.repository.VehicleRepository;
import com.emp.management.service.custom.EmployeeService;
import com.emp.management.util.exception.EmployeeNotFoundException;
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
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper mapper;
    private final EmployeeRepository employeeRepository;
    private final VehicleRepository vehicleRepository;

    /**
     * Performs Employee Save Operation
     */
    @Transactional
    @Override
    public void save(EmployeeDTO data) throws RuntimeException {
        log.info("Employee: " + data);
        try {
            if (employeeRepository.existsByEmail(data.getEmail()))
                throw new RuntimeException("Employee already exists!");

            Employee employee = mapper.map(data, Employee.class);
            if (data.getVehicle() != null) {
                Vehicle vehicle = mapper.map(data.getVehicle(), Vehicle.class);
                if (!vehicleRepository.existsById(vehicle.getId())) {
                    log.info("Vehicle: " + data.getVehicle());
                    vehicle = vehicleRepository.save(mapper.map(data.getVehicle(), Vehicle.class));
                    log.info("Vehicle saved: " + employee.getVehicle());
                }
                log.info("vehicle setted");
                employee.setVehicle(vehicleRepository.findById(vehicle.getId()).get());
            }

            employeeRepository.save(employee);
            log.info("Employee saved ");
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * PERFORMS EMPLOYEE UPDATE OPERATION
     * @param data
     * @throws EmployeeNotFoundException
     */
    @Transactional
    @Override
    public void update(EmployeeDTO data) throws EmployeeNotFoundException {
        log.info("Updating employee: " + data);

        try {
            if (!employeeRepository.existsById(data.getId())) throw new RuntimeException("Employee not exists!");
            Employee employee = mapper.map(data, Employee.class);
            vehicleRepository.findVehicleByEmployeeId(employee.getId()).ifPresent(vehicle -> employee.setVehicle(vehicle));
            employeeRepository.save(employee);
            log.info("Employee updated: " + employee);
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * DELETE EMPLOYEE BY ID
     * @param id
     * @throws EmployeeNotFoundException
     */
    @Transactional
    @Override
    public void delete(Long id) throws EmployeeNotFoundException {
        log.info("Deleting employee with id: " + id);

        try {
            log.info("Deleting employee with id: " + id);
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                return;
            }
            throw new EmployeeNotFoundException("Employee not found");
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * FIND EMPLOYEE BY ID
     * @param id
     * @return
     * @throws EmployeeNotFoundException
     */
    @Override
    public EmployeeDTO findById(Long id) throws EmployeeNotFoundException {
        log.info("Fetching employee with id: " + id);

        try {
            Optional<Employee> employeeById = employeeRepository.findEmployeeById(id);
            if (employeeById.isPresent()) {
                return EmployeeDTO.builder().id(employeeById.get().getId()).firstname(employeeById.get().getFirstname()).lastname(employeeById.get().getLastname()).email(employeeById.get().getEmail()).dob(employeeById.get().getDob()).build();
            }
            throw new EmployeeNotFoundException("Employee not found");
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }

    }

    /**
     * FETCH ALL EMPLOYEES
     * @return
     */
    @Override
    public List<EmployeeDTO> findAll() {
        log.info("Fetching all employees");
        try {
            return employeeRepository.findAll().stream().map(employee -> mapper.map(employee, EmployeeDTO.class)).toList();
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }

    }

    /**
     * FETCH ALL EMPLOYEES IN EMPLOYEE DTO TYPE
     * @return
     */
    @Override
    public List<EmployeeDTO> getAllEmployeeInEmployeeDTOType() {
        log.info("Fetching all employees in EmployeeDTO type");
        try {
            return employeeRepository.getAllEmployeeInEmployeeDTOType();
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * FETCH EMPLOYEE LIST WITH CITY, EMAIL, FIRSTNAME, LASTNAME
     * @param city
     * @param email
     * @param firstname
     * @param lastname
     * @param pageable
     * @return
     */
    @Override
    public Page<EmployeeDTO> getEmployeeList(String city, String email, String firstname, String lastname, Pageable pageable) {
        log.info("Fetching employee list with city: {}, email: {}, firstname: {}, lastname: {}", city, email, firstname, lastname);
        try {
            return employeeRepository.getEmployeeList(city, email, firstname, lastname, pageable);
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }
}
