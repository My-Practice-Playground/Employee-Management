package com.emp.management.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.sql.Date;

@Entity
public class Task {
    @Id
    private Long id;
    private String name;
    private String description;
    private String status;
    private String priority;
    private Date dueDate;
    private String notes;
    private String browser;
    private String os;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE,
            CascadeType.DETACH
    })
    private Employee employee;

}
