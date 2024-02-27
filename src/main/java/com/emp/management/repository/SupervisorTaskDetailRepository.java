package com.emp.management.repository;

import com.emp.management.entity.SupervisorTaskDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lakshan Chamoditha Perera
 * @project management
 * @createdBy IntelliJ IDEA
 * @created 2024-02-27 - 13.55
 **/


@Repository
public interface SupervisorTaskDetailRepository extends JpaRepository<SupervisorTaskDetail, Integer> {
}
