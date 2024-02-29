package com.emp.management.controller;

import com.emp.management.dto.EmployeeDTO;
import com.emp.management.service.custom.EmployeeService;
import com.emp.management.util.payload.respond.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

/*
SAVE EMPLOYEE
**/
    @PostMapping
    public ResponseEntity<StandardResponse> saveEmployee(@RequestBody @Valid EmployeeDTO employee) {
        log.info("Employee: {}", employee);

        employeeService.save(employee);
        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Saved", null), HttpStatus.CREATED);
    }

    /*
GET ALL EMPLOYEES
**/
    @GetMapping
    public ResponseEntity<StandardResponse> getAllEmployees() {
        log.info("Fetching all employees");

        return new ResponseEntity<>(
                new StandardResponse(200, "Employees Fetched",
                        employeeService.getAllEmployeeInEmployeeDTOType()), HttpStatus.OK);
    }

/*
DELETE EMPLOYEE BY ID
**/
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with id: {}", id);

        employeeService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Deleted", null), HttpStatus.OK);

    }

/*
GET EMPLOYEE BY ID
**/
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with id: {}", id);

        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Fetched", employeeService.findById(id)), HttpStatus.OK);
    }

/*
UPDATE EMPLOYEE BY ID
**/
    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employee) {
        log.info("Updating employee with id: {}", id);

        employee.setId(id);
        employeeService.update(employee);
        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Updated", null), HttpStatus.OK);
    }


}
