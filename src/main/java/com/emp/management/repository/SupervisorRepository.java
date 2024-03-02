package com.emp.management.repository;

import com.emp.management.dto.SupervisorDTO;
import com.emp.management.entity.Supervisor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * @param city
     * @param email
     * @param firstname
     * @param lastname
     * @param salary
     * @param pageable
     * @return Page of SupervisorDTO
     */
    @Query("SELECT new com.emp.management.dto.SupervisorDTO(s.id,s.email,s.firstname,s.lastname,s.city,s.salary) FROM Supervisor s " +
           "WHERE " +
           "(?1 IS NULL OR LOWER(s.city) LIKE LOWER(concat('%', ?1, '%'))) " +
           "AND (?2 IS NULL OR LOWER(s.email) LIKE LOWER(concat('%', ?2, '%'))) " +
           "AND (?3 IS NULL OR LOWER(s.firstname) LIKE LOWER(concat('%', ?3, '%'))) " +
           "AND (?4 IS NULL OR LOWER(s.lastname) LIKE LOWER(concat('%', ?4, '%'))) " +
           "AND (?5 IS NULL OR s.salary = ?5)"
    )
    Page<SupervisorDTO> getSupervisors(String city, String email, String firstname, String lastname, Double salary, Pageable pageable);
}
