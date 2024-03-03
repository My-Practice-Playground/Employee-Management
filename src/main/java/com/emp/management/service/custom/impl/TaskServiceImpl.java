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

    /**
     * PERFORMS TASK SAVE OPERATION
     *
     */
    @Transactional
    @Override
    public void save(TaskDTO data) {
        log.info("Task: {}", data);

        try {
            taskRepository.save(mapper.map(data, Task.class));
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * PERFORMS TASK UPDATE OPERATION
     *
     * @param data
     */
    @Override
    public void update(TaskDTO data) {
        log.info("Task: {}", data);
        try {
            taskRepository.save(
                    mapper.map(taskRepository.findById(data.getId())
                            .orElseThrow(() -> new RuntimeException("Task not found for id: " + data.getId())),
                            Task.class));

            log.info("Task updated successfully");
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * PERFORMS TASK DELETE OPERATION
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        log.info("Deleting task with id: {}", id);

        try{
            Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found for id: " + id));
            taskRepository.delete(task);
            log.info("Task deleted successfully");
        }catch (Exception e){
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * FETCHES TASK BY ID
     *
     * @param id
     * @return TaskDTO
     */
    @Override
    public TaskDTO findById(Long id) {
        log.info("Fetching task with id: {}", id);

        try{
            Optional<Task> taskById = taskRepository.findById(id);
                return mapper.map(
                        taskById.orElseThrow(() -> new RuntimeException("Task not found for id: " + id)).getId(), TaskDTO.class);
        }catch (Exception e){
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * FETCHES ALL TASKS
     *
     * @return List<TaskDTO>
     */
    @Override
    public List<TaskDTO> findAll() {
        log.info("Fetching all tasks");

        try {
            return taskRepository.findAll().stream().map(task -> mapper.map(task, TaskDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }
}
