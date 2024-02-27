package com.emp.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String status;
    private String notes;
    private String os;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Employee employee;

    @ToString.Exclude
    @OneToMany(mappedBy = "task",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List <SupervisorTaskDetail> supervisor_task_details;

}
