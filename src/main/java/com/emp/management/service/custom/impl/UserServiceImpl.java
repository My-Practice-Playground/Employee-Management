package com.emp.management.service.custom.impl;

import com.emp.management.repository.UserRepository;
import com.emp.management.service.custom.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-29 - 11.46
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Boolean existsByEmail(String email) {
        log.info("Checking email: " + email);

        return userRepository.existsByEmail(email);
    }
}
