package com.emp.management.controller;

import com.emp.management.dto.TaskDTO;
import com.emp.management.service.custom.TaskService;
import com.emp.management.util.payload.respond.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<StandardResponse> saveTask(@RequestBody @Valid TaskDTO taskDTO) {
        log.info("Task: {}", taskDTO);

        taskService.save(taskDTO);
        return ResponseEntity.ok(
                new StandardResponse(200, "Task Saved", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteTask(@PathVariable Long id) {
        log.info("Deleting task with id: {}", id);

        taskService.delete(id);
        return ResponseEntity.ok(
                new StandardResponse(200, "Task Deleted", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getTaskById(@PathVariable Long id) {
        log.info("Fetching task with id: {}", id);

        return ResponseEntity.ok(
                new StandardResponse(200, "Task Fetched", taskService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllTasks() {
        log.info("Fetching all tasks");

        return ResponseEntity.ok(
                new StandardResponse(200, "Tasks Fetched", taskService.findAll()));
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateTask(@RequestBody @Valid TaskDTO taskDTO) {
        log.info("Task: {}", taskDTO);

        taskService.update(taskDTO);
        return ResponseEntity.ok(
                new StandardResponse(200, "Task Updated", null));
    }


}
