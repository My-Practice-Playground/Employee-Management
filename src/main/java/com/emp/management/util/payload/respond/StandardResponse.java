package com.emp.management.util.payload.respond;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StandardResponse {
    private Integer status;
    private String message;
    private Object data;
}
