package com.emp.management.advicer;

import com.emp.management.util.payload.respond.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionHandle {

    // global Exception handler for MethodArgumentNotValidException
    @ExceptionHandler
    public ResponseEntity<StandardResponse> handleException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                new StandardResponse(
                        400,
                        e.getBindingResult().getFieldError().getDefaultMessage(),
                        null), BAD_REQUEST);
    }
}
