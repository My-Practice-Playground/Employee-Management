package com.emp.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private Date manufactureDate;
    private String color;

    @OneToOne(mappedBy = "vehicle",fetch = FetchType.LAZY)
    @ToString.Exclude
    private Employee employee;
}
