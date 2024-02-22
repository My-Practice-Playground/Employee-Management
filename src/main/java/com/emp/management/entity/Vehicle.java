package com.emp.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    private Long id;
    private String make;
    private String model;
    private Date manufactureDate;
    private String color;
    @OneToOne(mappedBy = "vehicle")
    @ToString.Exclude
    private Employee employee;
}
