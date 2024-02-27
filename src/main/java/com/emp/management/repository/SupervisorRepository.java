package com.emp.management.repository;

import com.emp.management.dto.SupervisorDTO;
import com.emp.management.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 12.49
 **/

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor,Long> {

    Boolean existsByEmail(String email);
}
