package com.emp.management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotNull(message = "First name cannot be null")
    private String firstname;
    @NotNull(message = "Last name cannot be null")
    private String lastname;
    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
    private String email;
    @NotNull(message = "Date of birth cannot be null")
    private Date dob;
    private VehicleDTO vehicle;

    public EmployeeDTO(Long id, String firstname, String lastname, String email, Date dob) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dob = dob;
    }

}
