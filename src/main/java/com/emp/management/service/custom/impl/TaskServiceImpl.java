package com.emp.management.service.custom.impl;

import com.emp.management.entity.Task;
import com.emp.management.repository.EmployeeRepository;
import com.emp.management.repository.TaskRepository;
import com.emp.management.service.custom.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ModelMapper mapper;
    private final TaskRepository taskRepository;
    @Override
    public void save(Task data) {

    }

    @Override
    public void update(Task data) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Task findById(Long id) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }
}
