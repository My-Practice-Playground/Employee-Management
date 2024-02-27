package com.emp.management.controller;

import com.emp.management.dto.SupervisorTaskDetailDTO;
import com.emp.management.service.custom.SupervisorTaskDetailService;
import com.emp.management.util.payload.respond.StandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 13.54
 **/

@Slf4j
@RestController
@RequestMapping("api/task-reports")
@RequiredArgsConstructor
public class SupervisorTaskDetailController {
    private final SupervisorTaskDetailService supervisorTaskDetailService;

/*
SAVE SUPERVISOR TASK DETAIL
* */
    @PostMapping
    public ResponseEntity<StandardResponse> saveTask(@RequestBody SupervisorTaskDetailDTO supervisorTaskDetailDTO) {
        log.info("Task: {}", supervisorTaskDetailDTO);

        supervisorTaskDetailService.save(supervisorTaskDetailDTO);
        return ResponseEntity.ok(new StandardResponse(200, "Task Saved", null));
    }

/*
UPDATE SUPERVISOR TASK DETAIL
* */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteTask(@PathVariable Integer id) {
        log.info("Deleting task with id: {}", id);

        supervisorTaskDetailService.delete(id);
        return ResponseEntity.ok(new StandardResponse(200, "Task Deleted", null));
    }

/*
GET SUPERVISOR TASK DETAIL BY ID
* */
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getTaskById(@PathVariable Integer id) {
        log.info("Fetching task with id: {}", id);

        return ResponseEntity.ok(new StandardResponse(200, "Task Fetched", supervisorTaskDetailService.findById(id)));
    }

/*
GET ALL SUPERVISOR TASK DETAIL
* */
    @GetMapping
    public ResponseEntity<StandardResponse> getAllTasks() {
        log.info("Fetching all tasks");

        return ResponseEntity.ok(new StandardResponse(200, "Task Fetched", supervisorTaskDetailService.findAll()));
    }
}
