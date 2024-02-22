package com.emp.management.advicer;

import com.emp.management.util.payload.respond.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    // Global Exception handler for MethodArgumentNotValidException
    @ExceptionHandler
    public ResponseEntity<StandardResponse> handleException(MethodArgumentNotValidException e) {
       logger.info(e.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<>(
                new StandardResponse(400, e.getBindingResult().getFieldError().getDefaultMessage(),null),
                BAD_REQUEST
        );
    }

    // Global Exception handler for RuntimeException
    @ExceptionHandler
    public ResponseEntity<StandardResponse> handleException(RuntimeException e) {
        logger.info(e.getMessage());
        return new ResponseEntity<>(new StandardResponse(400, e.getMessage(), null), BAD_REQUEST);
    }
}
