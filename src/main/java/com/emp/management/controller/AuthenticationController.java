package com.emp.management.controller;

import com.emp.management.service.custom.UserService;
import com.emp.management.service.security.AuthenticationService;
import com.emp.management.util.payload.request.LoginRequest;
import com.emp.management.util.payload.request.RegisterRequest;
import com.emp.management.util.payload.respond.AuthenticationResponse;
import com.emp.management.util.payload.respond.StandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    /**
     * REGISTER USER
     * @param request
     * @return ResponseEntity<StandardResponse>
     */
    @PostMapping("/register")
    public ResponseEntity<StandardResponse> register(@RequestBody RegisterRequest request) {
        log.info("register {} " + request);

        if (!userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.ok(
                    StandardResponse.builder()
                            .message("User registered successfully")
                            .status(200)
                            .data(AuthenticationResponse.builder()
                                    .token(authenticationService
                                            .register(request))
                                    .build())
                            .build());
        }
        return ResponseEntity.ok(StandardResponse.builder()
                .message("User already exists.")
                .status(400)
                .build());
    }

    /**
     * LOGIN USER AND GENERATE TOKEN
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        log.info("login {} " + request);

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
