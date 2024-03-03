package com.emp.management.service.custom;

import com.emp.management.dto.TaskDTO;
import com.emp.management.entity.Task;
import com.emp.management.service.SuperService;
import org.springframework.data.domain.Page;

public interface TaskService extends SuperService<TaskDTO, Long> {
    /**
     * GET TASKS BY DESCRIPTION, NAME, NOTES, OS and STATUS
     * @param description
     * @param name
     * @param notes
     * @param os
     * @param status
     * @param page
     * @param size
     * @return
     */
    Page<TaskDTO> getTaskList(String description, String name, String notes, String os,String status, Integer page, Integer size);
}
