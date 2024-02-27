package com.emp.management.service.custom.impl;

import com.emp.management.dto.SupervisorDTO;
import com.emp.management.repository.SupervisorRepository;
import com.emp.management.service.custom.SupervisorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 12.48
 **/


@Slf4j
@Service
@RequiredArgsConstructor
public class SupervisorServiceImpl implements SupervisorService {
    private final SupervisorRepository supervisorRepository;
    @Override
    public void save(SupervisorDTO data) {

    }

    @Override
    public void update(SupervisorDTO data) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public SupervisorDTO findById(Long id) {
        return null;
    }

    @Override
    public List<SupervisorDTO> findAll() {
        return null;
    }
}
