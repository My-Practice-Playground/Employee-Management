package com.emp.management.entity;

import jakarta.persistence.*;
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
public class SupervisorTaskDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Supervisor supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Task task;

    private String status;

    private Boolean isCompleted;

    @CreationTimestamp
    private Date assignedDate;
}
