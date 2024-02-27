package com.emp.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 12.16
 **/


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Supervisor_Task_Detail {
    @ManyToOne(fetch = FetchType.EAGER)
    Supervisor supervisor;
    @ManyToOne(fetch = FetchType.EAGER)
    Task task;

    String status;
    Boolean isCompleted;
    @CreationTimestamp
    Date assignedDate;
    @Id
    private Long id;

}
