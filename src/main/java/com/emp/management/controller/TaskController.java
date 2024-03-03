package com.emp.management.controller;

import com.emp.management.dto.TaskDTO;
import com.emp.management.service.custom.TaskService;
import com.emp.management.util.payload.respond.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    /**
     * SAVE TASK
     * @param taskDTO
     * @return ResponseEntity<StandardResponse>
     */
    @PostMapping
    public ResponseEntity<StandardResponse> saveTask(@RequestBody @Valid TaskDTO taskDTO) {
        log.info("Task: {}", taskDTO);

        taskService.save(taskDTO);
        return ResponseEntity.ok(
                new StandardResponse(200, "Task Saved", null));
    }

    /**
     * DELETE TASK BY ID
     * @param id
     * @return ResponseEntity<StandardResponse>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteTask(@PathVariable Long id) {
        log.info("Deleting task with id: {}", id);

        taskService.delete(id);
        return ResponseEntity.ok(
                new StandardResponse(200, "Task Deleted", null));
    }

    /**
     * GET TASK BY ID
     * @param id
     * @return ResponseEntity<StandardResponse>
     */
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getTaskById(@PathVariable Long id) {
        log.info("Fetching task with id: {}", id);

        return ResponseEntity.ok(
                new StandardResponse(200, "Task Fetched", taskService.findById(id)));
    }

    /**
     * GET ALL TASKS
     * @return  ResponseEntity<StandardResponse>
     */
//@PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<StandardResponse> getAllTasks() {
        log.info("Fetching all tasks");

        return ResponseEntity.ok(
                new StandardResponse(200, "Tasks Fetched", taskService.findAll()));
    }

    /**
     * UPDATE TASK
     * @param taskDTO
     * @return ResponseEntity<StandardResponse>
     */
    @PutMapping
    public ResponseEntity<StandardResponse> updateTask(@RequestBody @Valid TaskDTO taskDTO) {
        log.info("Task: {}", taskDTO);

        taskService.update(taskDTO);
        return ResponseEntity.ok(
                new StandardResponse(200, "Task Updated", null));
    }

    /**
     * FILTER TASKS BY DESCRIPTION, NAME, NOTES, OS and STATUS
     * @param description
     * @param name
     * @param notes
     * @param os
     * @param status
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/filter")
    public ResponseEntity<StandardResponse> getTaskList(
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String notes,
            @RequestParam(required = false) String os,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        log.info("Fetching task list");

        return ResponseEntity.ok(
                new StandardResponse(200, "Task List Fetched",
                        taskService.getTaskList(description, name, notes, os, status, page, size)));
    }


}
