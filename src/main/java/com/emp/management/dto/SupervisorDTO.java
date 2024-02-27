package com.emp.management.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 12.46
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDTO {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String city;
    private Double salary;

}
