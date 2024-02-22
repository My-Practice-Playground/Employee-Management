package com.emp.management.controller;

import com.emp.management.service.custom.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
}
