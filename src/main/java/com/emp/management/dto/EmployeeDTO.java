package com.emp.management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.sql.Date;

@Data
@Builder
@RequiredArgsConstructor
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
    private String city;

    private VehicleDTO vehicle;

    public EmployeeDTO(Long id, String firstname, String lastname, String email, Date dob, String city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dob = dob;
        this.city=city;
    }
    public EmployeeDTO(Long id, String firstname, String lastname, String email, Date dob, String city,VehicleDTO vehicleDTO) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dob = dob;
        this.city=city;
        this.vehicle = vehicleDTO;
    }

}
