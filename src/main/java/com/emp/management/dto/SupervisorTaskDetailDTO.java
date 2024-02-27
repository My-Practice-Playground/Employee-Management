package com.emp.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 13.47
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorTaskDetailDTO {
    private SupervisorDTO supervisor;
    private TaskDTO task;
    private String status;
    private Boolean isCompleted;
    private Date assignedDate;
    private Integer id;
}
