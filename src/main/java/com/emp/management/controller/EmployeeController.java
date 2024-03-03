package com.emp.management.controller;

import com.emp.management.dto.EmployeeDTO;
import com.emp.management.service.custom.EmployeeService;
import com.emp.management.util.payload.respond.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * SAVE EMPLOYEE
     * @param employee
     * @return ResponseEntity<StandardResponse>
     */
    @PostMapping
    public ResponseEntity<StandardResponse> saveEmployee(@RequestBody @Valid EmployeeDTO employee) {
        log.info("Employee: {}", employee);

        employeeService.save(employee);
        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Saved", null), HttpStatus.CREATED);
    }

    /**
     * GET ALL EMPLOYEES
     * @return ResponseEntity<StandardResponse>
     */
    @GetMapping
    public ResponseEntity<StandardResponse> getAllEmployees() {
        log.info("Fetching all employees");

        return new ResponseEntity<>(
                new StandardResponse(200, "Employees Fetched",
                        employeeService.getAllEmployeeInEmployeeDTOType()), HttpStatus.OK);
    }

    /**
     * DELETE EMPLOYEE BY ID
     * @param id
     * @return ResponseEntity<StandardResponse>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with id: {}", id);

        employeeService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Deleted", null), HttpStatus.OK);

    }

    /**
     * GET EMPLOYEE BY ID
      * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with id: {}", id);

        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Fetched", employeeService.findById(id)), HttpStatus.OK);
    }

    /**
     * UPDATE EMPLOYEE
     * @param id
     * @param employee
     * @return ResponseEntity<StandardResponse>
     */
    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employee) {
        log.info("Updating employee with id: {}", id);

        employee.setId(id);
        employeeService.update(employee);
        return new ResponseEntity<>(
                new StandardResponse(200, "Employee Updated", null), HttpStatus.OK);
    }

    /**
     * GET EMPLOYEE LIST WITH FILTER
     * @param city
     * @param email
     * @param firstname
     * @param lastname
     * @param page
     * @param size
     * @return ResponseEntity<StandardResponse>
     */
    @GetMapping("/filter")
    public ResponseEntity<StandardResponse> getEmployeeList(@RequestParam(required = false) String city,
                                                            @RequestParam(required = false) String email,
                                                            @RequestParam(required = false) String firstname,
                                                            @RequestParam(required = false) String lastname,
                                                            @RequestParam(defaultValue = "0") Integer page,
                                                            @RequestParam(defaultValue = "10") Integer size) {
        log.info("Fetching employee list with city: {}, email: {}, firstname: {}, lastname: {}", city, email, firstname, lastname);
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return new ResponseEntity<>(
                new StandardResponse(200, "Employee List Fetched",
                        employeeService.getEmployeeList(city, email, firstname, lastname,pageable)), HttpStatus.OK);
    }

    /**
     * GET SUPERVISOR LIST ASSOCIATED WITH EMPLOYEE
     * @param id
     * @param page
     * @param size
     * @return ResponseEntity<StandardResponse>
     */
    @GetMapping("/supervisor")
    public ResponseEntity<StandardResponse> getSupervisorList(@RequestParam Long id,
                                                              @RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer size) {
        log.info("Fetching supervisor list with id: {}", id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Supervisor List Fetched",
                        employeeService.getSupervisorList(id, PageRequest.of(page, size))), HttpStatus.OK);
    }


}
