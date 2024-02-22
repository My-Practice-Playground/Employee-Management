package com.emp.management.dto;

import com.emp.management.util.annotation.ValidateDate;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private Long id;

    private String make;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+$", message = "Invalid model")
    private String model;

    @ValidateDate(message = "Invalid date")
    private Date manufactureDate;

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+$", message = "Invalid color")
    private String color;

    private EmployeeDTO employee;
}

//regex for sql date ->