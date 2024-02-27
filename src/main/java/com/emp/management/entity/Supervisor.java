package com.emp.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 12.15
 **/


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Supervisor {
    @Id
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String city;
    private Double salary;

    @ToString.Exclude
    @OneToMany(mappedBy = "supervisor",fetch = FetchType.EAGER)
    private List<Supervisor_Task_Detail> supervisorTaskDetails;
}
