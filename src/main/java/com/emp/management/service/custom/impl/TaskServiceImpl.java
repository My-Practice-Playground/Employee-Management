package com.emp.management.service.custom.impl;

import com.emp.management.dto.TaskDTO;
import com.emp.management.entity.Task;
import com.emp.management.repository.TaskRepository;
import com.emp.management.service.custom.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ModelMapper mapper;
    private final TaskRepository taskRepository;


    @Transactional
    @Override
    public void save(TaskDTO data) {
        log.info("Task: {}", data);

        taskRepository.save(mapper.map(data, Task.class));
    }

    @Override
    public void update(TaskDTO data) {
        log.info("Task: {}", data);

        taskRepository.save(mapper.map(data, Task.class));
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting task with id: {}", id);

        Optional<Task> taskById = taskRepository.findById(id);
        if (taskById.isPresent()) {
            taskRepository.delete(taskById.get());
            return;
        }
        throw new RuntimeException("Task not found for id: " + id);

    }

    @Override
    public TaskDTO findById(Long id) {
        log.info("Fetching task with id: {}", id);

        Optional<Task> taskById = taskRepository.findById(id);
        if (taskById.isPresent()) {
            return mapper.map(taskById.get(), TaskDTO.class);
        }
        throw new RuntimeException("Task not found for id: " + id);
    }

    @Override
    public List<TaskDTO> findAll() {
        log.info("Fetching all tasks");

        List<Task> taskList = taskRepository.findAll();
        return taskList.stream().map(task -> mapper.map(task, TaskDTO.class)).collect(Collectors.toList());
    }
}
