package com.emp.management.service.security;


import com.emp.management.entity.User;
import com.emp.management.repository.UserRepository;
import com.emp.management.util.enums.Role;
import com.emp.management.util.payload.request.LoginRequest;
import com.emp.management.util.payload.request.RegisterRequest;
import com.emp.management.util.payload.respond.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

/*
SAVE USER & GENERATE TOKEN
* */
    public String register(RegisterRequest request) {
        log.info("register {} " + request);

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }


/*
AUTHENTICATE USER
* */
    public AuthenticationResponse authenticate(LoginRequest request) {
        log.info("authenticate {} " + request);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> {
            log.error("User not found");
            return new UsernameNotFoundException("User not found");
        });
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
