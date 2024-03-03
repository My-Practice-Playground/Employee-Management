package com.emp.management.repository;

import com.emp.management.dto.TaskDTO;
import com.emp.management.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * FILTER TASKS BY DESCRIPTION, NAME, NOTES, OS and STATUS
     * @param description
     * @param name
     * @param notes
     * @param os
     * @param status
     * @param pageable
     * @return Page of Task
     */
    @Query(" SELECT new com.emp.management.dto.TaskDTO(t.id,t.name,t.description,t.status,t.os) FROM Task t " +
            "WHERE " +
            "(:description IS NULL OR LOWER(t.description) LIKE LOWER(concat('%', :description, '%'))) " +
            "AND (:name IS NULL OR LOWER(t.name) LIKE LOWER(concat('%', :name, '%'))) " +
            "AND (:notes IS NULL OR LOWER(t.notes) LIKE LOWER(concat('%', :notes, '%'))) " +
            "AND (:os IS NULL OR LOWER(t.os) LIKE LOWER(concat('%', :os, '%')))"+
            "AND (:status IS NULL OR LOWER(t.status) LIKE LOWER(concat('%', :status, '%'))) "
    )
    Page<TaskDTO> getTaskList(String description, String name, String notes, String os, String status, Pageable pageable);


}
