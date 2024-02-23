package com.emp.management.controller;

import com.emp.management.service.security.AuthenticationService;
import com.emp.management.util.payload.request.LoginRequest;
import com.emp.management.util.payload.request.RegisterRequest;
import com.emp.management.util.payload.respond.AuthenticationResponse;
import com.emp.management.util.payload.respond.StandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

/*
REGISTER USER
* */
    @PostMapping("/register")
    public ResponseEntity<StandardResponse> register(@RequestBody RegisterRequest request) {
        log.info("/register");

        var data = StandardResponse.builder()
                .message("User registered successfully")
                .status(200)
                .data(AuthenticationResponse.builder()
                        .token(authenticationService.register(request))
                        .build())
                .build();
        return ResponseEntity.ok(data);
    }

/*
LOGIN
* */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(LoginRequest request) {
        log.info("login {} " + request);

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
