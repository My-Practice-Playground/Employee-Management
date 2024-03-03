package com.emp.management.service.custom;

import com.emp.management.dto.SupervisorDTO;
import com.emp.management.service.SuperService;
import org.springframework.data.domain.Page;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 12.45
 **/


public interface SupervisorService extends SuperService<SupervisorDTO, Long> {

    /**
     * GET SUPERVISORS BY CITY, EMAIL, FIRSTNAME, LASTNAME, SALARY
     * @param city
     * @param email
     * @param firstname
     * @param lastname
     * @param salary
     * @param page
     * @param size
     * @return
     */
    Page<SupervisorDTO> getSupervisors(String city, String email, String firstname, String lastname, Double salary, Integer page, Integer size);
}
