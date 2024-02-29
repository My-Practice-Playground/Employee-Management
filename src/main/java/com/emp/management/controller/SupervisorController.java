package com.emp.management.controller;

import com.emp.management.dto.SupervisorDTO;
import com.emp.management.service.custom.SupervisorService;
import com.emp.management.util.payload.respond.StandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 12.44
 **/

@Slf4j
@RestController
@RequestMapping("api/supervisors")
@RequiredArgsConstructor
public class SupervisorController {
    private final SupervisorService supervisorService;

/*
SAVE SUPERVISOR
* */
    @PostMapping
    public ResponseEntity<StandardResponse> saveSupervisor(@RequestBody SupervisorDTO supervisorDTO) {
        supervisorService.save(supervisorDTO);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.CREATED.value(), "Supervisor Saved", ""), HttpStatus.OK);
    }

/*
GET ALL SUPERVISORS
* */
    @GetMapping
    public ResponseEntity<StandardResponse> getAllSupervisors() {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "Supervisor List Fetched", supervisorService.findAll()), HttpStatus.OK);
    }

/*
GET SUPERVISOR BY ID
* */
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getSupervisorById(@PathVariable Long id) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "Supervisor Fetched", supervisorService.findById(id)), HttpStatus.OK);
    }

/*
UPDATE SUPERVISOR
* */
    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateSupervisor(@RequestBody SupervisorDTO supervisorDTO) {
        supervisorService.update(supervisorDTO);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "Supervisor Updated", ""), HttpStatus.OK);
    }

/*
DELETE SUPERVISOR
* */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteSupervisor(@RequestParam Long id) {
        supervisorService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(), "Supervisor Deleted", ""), HttpStatus.OK);
    }




}