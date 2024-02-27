package com.emp.management.service.custom.impl;

import com.emp.management.dto.SupervisorDTO;
import com.emp.management.entity.Supervisor;
import com.emp.management.repository.SupervisorRepository;
import com.emp.management.service.custom.SupervisorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    private final ModelMapper mapper;

    @Transactional
    @Override
    public void save(SupervisorDTO data) {
        log.info("Supervisor: {}", data);

        if (supervisorRepository.existsByEmail(data.getEmail()))
            throw new RuntimeException("Supervisor already exists");

        supervisorRepository.save(mapper.map(data, Supervisor.class));
    }

    @Transactional
    @Override
    public void update(SupervisorDTO data) {
        log.info("Supervisor: {}", data);

        if (!supervisorRepository.existsById(data.getId())) {
            log.error("Supervisor not found");
            throw new RuntimeException("Supervisor not found");
        }

        Optional<Supervisor> byId = supervisorRepository.findById(data.getId());
        byId.get().setFirstname(data.getFirstname());
        byId.get().setLastname(data.getLastname());
        byId.get().setEmail(data.getEmail());
        byId.get().setSalary(data.getSalary());
        byId.get().setCity(data.getCity());

        supervisorRepository.save(byId.get());

        log.info("Supervisor updated");
    }


    @Transactional
    @Override
    public void delete(Long id) {
        log.info("Supervisor id: {}", id);
        if (supervisorRepository.existsById(id)) {
            log.info("Supervisor found");
            supervisorRepository.deleteById(id);
            log.info("Supervisor deleted");
        } else throw new RuntimeException("Supervisor not found");

    }

    @Override
    public SupervisorDTO findById(Long id) {
        log.info("find by if {}" + id);

        Supervisor supervisor = supervisorRepository.findById(id).orElseThrow(() -> new RuntimeException("Supervisor not found"));
        return mapper.map(supervisor, SupervisorDTO.class);
    }

    @Override
    public List<SupervisorDTO> findAll() {
        log.info("find all {}");

        List<Supervisor> supervisorList = supervisorRepository.findAll();
        return supervisorList.stream().map(supervisor -> mapper.map(supervisor, SupervisorDTO.class)).toList();
    }
}
