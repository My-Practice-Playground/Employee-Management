package com.emp.management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+$", message = "Invalid name")
    private String name;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+$", message = "Invalid description")
    private String description;
    @NotNull
    private String status;
    private String os;

    private EmployeeDTO employee;

   public TaskDTO(Long id, String name, String description, String status, String os) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.os = os;
    }

}

