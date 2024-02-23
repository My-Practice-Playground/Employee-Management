package com.emp.management.util.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
