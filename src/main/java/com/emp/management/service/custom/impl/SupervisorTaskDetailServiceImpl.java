package com.emp.management.service.custom.impl;

import com.emp.management.dto.SupervisorTaskDetailDTO;
import com.emp.management.entity.Supervisor;
import com.emp.management.entity.SupervisorTaskDetail;
import com.emp.management.entity.Task;
import com.emp.management.repository.SupervisorRepository;
import com.emp.management.repository.SupervisorTaskDetailRepository;
import com.emp.management.repository.TaskRepository;
import com.emp.management.service.custom.SupervisorTaskDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 13.58
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class SupervisorTaskDetailServiceImpl implements SupervisorTaskDetailService {
    private final SupervisorTaskDetailRepository supervisorTaskDetailRepository;
    private final SupervisorRepository supervisorRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    /*
    SAVE SUPERVISOR TASK DETAIL
    * */
    @Transactional
    @Override
    public void save(SupervisorTaskDetailDTO data) {
        log.info("Supervisor Task Detail: {}", data);
        try {
            Supervisor supervisorNotFound = supervisorRepository.findById(data.getSupervisor().getId()).orElseThrow(() -> new RuntimeException("Supervisor not found"));

            Task taskNotFound = taskRepository.findById(data.getTask().getId()).orElseThrow(() -> new RuntimeException("Task not found"));

            SupervisorTaskDetail map = modelMapper.map(data, SupervisorTaskDetail.class);
            map.setSupervisor(supervisorNotFound);
            map.setTask(taskNotFound);
            supervisorTaskDetailRepository.save(map);
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * UPDATE SUPERVISOR TASK DETAIL
     *
     * @param data
     */
    @Transactional
    @Override
    public void update(SupervisorTaskDetailDTO data) {
        log.info("Supervisor Task Detail: {}", data);
        try {
            SupervisorTaskDetail supervisorTaskDetail = supervisorTaskDetailRepository.findById(data.getId()).orElseThrow(() -> {
                log.error("Supervisor Task Detail not found");
                return new RuntimeException("Supervisor Task Detail not found");
            });

//      In this scenario I am not going to update supervisor and task. Task status and isCompleted status are the only things that I am going to update
            supervisorTaskDetail.setStatus(data.getStatus());
            supervisorTaskDetail.setIsCompleted(data.getIsCompleted());
            supervisorTaskDetailRepository.save(supervisorTaskDetail);
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * DELETE SUPERVISOR TASK DETAIL
     *
     * @param id
     */
    @Transactional
    @Override
    public void delete(Integer id) {
        log.info("Deleting task with id: {}", id);
        try {

            supervisorTaskDetailRepository.findById(id).orElseThrow(() -> {
                log.error("Supervisor Task Detail not found");

                return new RuntimeException("Supervisor Task Detail not found");
            });

            supervisorTaskDetailRepository.deleteById(id);
            log.info("Supervisor Task Detail deleted");
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * FIND SUPERVISOR TASK DETAIL BY ID
     *
     * @param id
     * @return
     */
    @Override
    public SupervisorTaskDetailDTO findById(Integer id) {
        log.info("find by if {}", id);
        try{
            return modelMapper.map(supervisorTaskDetailRepository.findById(id).orElseThrow(() -> {
                log.error("Supervisor Task Detail not found");

                return new RuntimeException("Supervisor Task Detail not found");
            }), SupervisorTaskDetailDTO.class);
        }catch (Exception e){
            log.error("Error: ", e);
            throw e;
        }
    }

    /**
     * FIND ALL SUPERVISOR TASK DETAIL
     *
     * @return List<SupervisorTaskDetailDTO>
     */
    @Override
    public List<SupervisorTaskDetailDTO> findAll() {
        log.info("Fetching all tasks");
        try {
            return supervisorTaskDetailRepository.findAll().stream().map(supervisorTaskDetail -> modelMapper.map(supervisorTaskDetail, SupervisorTaskDetailDTO.class)).toList();
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
 }
}
