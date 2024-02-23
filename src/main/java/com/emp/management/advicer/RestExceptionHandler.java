package com.emp.management.advicer;

import com.emp.management.util.exception.VehicleNotFoundException;
import com.emp.management.util.payload.respond.StandardResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

/*
GLOBAL EXCEPTION HANDLER FOR METHOD ARGUMENT NOT VALID EXCEPTION
 */
    @ExceptionHandler
    public ResponseEntity<StandardResponse> handleException(MethodArgumentNotValidException e) {
       log.info(e.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<>(
                new StandardResponse(400, e.getBindingResult().getFieldError().getDefaultMessage(),null),
                BAD_REQUEST
        );
    }

/*
GLOBAL EXCEPTION HANDLER FOR RUNTIME EXCEPTION
* */
    @ExceptionHandler
    public ResponseEntity<StandardResponse> handleException(RuntimeException e) {
        log.info(e.getMessage());

        return new ResponseEntity<>(
                new StandardResponse(400, e.getMessage(), null), BAD_REQUEST);
    }

/*
GLOBAL EXCEPTION HANDLER FOR VEHICLE NOT FOUND EXCEPTION
* */
    @ExceptionHandler
    public ResponseEntity<StandardResponse> handleException(VehicleNotFoundException e) {
        log.info(e.getMessage());

        return new ResponseEntity<>(
                new StandardResponse(404, e.getMessage(), null), NOT_FOUND);
    }
}
