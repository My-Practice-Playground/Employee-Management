package com.emp.management.controller;

import com.emp.management.dto.EmployeeDTO;
import com.emp.management.service.custom.EmployeeService;
import com.emp.management.util.payload.respond.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    /*
    Save employee
    */
    @PostMapping
    public ResponseEntity<StandardResponse> saveEmployee(@RequestBody @Valid EmployeeDTO employee) {
        logger.info("Employee: {}", employee);

        employeeService.save(employee);
        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Saved", null), HttpStatus.CREATED);
    }

    /*
    Get all employees
     */
    @GetMapping
    public ResponseEntity<StandardResponse> getAllEmployees() {
        logger.info("Fetching all employees");

        return new ResponseEntity<>(
                new StandardResponse(200, "Employees Fetched",
                        employeeService.getAllEmployeeInEmployeeDTOType()), HttpStatus.OK);
    }

    /*
    Delete Employee by id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteEmployee(@PathVariable Long id) {
        logger.info("Deleting employee with id: {}", id);

        employeeService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Deleted", null), HttpStatus.OK);

    }

    /*
    Get employee by id
    */
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getEmployeeById(@PathVariable Long id) {
        logger.info("Fetching employee with id: {}", id);

        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Fetched", employeeService.findById(id)), HttpStatus.OK);
    }

    /*
    Update employee by id
     */
    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employee) {
        logger.info("Updating employee with id: {}", id);

        employee.setId(id);
        employeeService.update(employee);
        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Updated", null), HttpStatus.OK);
    }


}
