package com.emp.management.repository;

import com.emp.management.dto.EmployeeDTO;
import com.emp.management.dto.SupervisorDTO;
import com.emp.management.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * CHECK IF EMPLOYEE EXISTS BY ID
     * @param id
     * @return boolean
     */
    boolean existsById(Long id);

    /**
     * FIND EMPLOYEE BY EMAIL
     * @param id
     * @return Optional<Employee>
     */
    Optional<Employee> findEmployeeById(Long id);

    /**
     * CHECK IF EMPLOYEE EXISTS BY EMAIL
     * @param email
     * @return boolean
     */
    boolean existsByEmail(String email);

    /**
     * USING JPQL QUERY TO GET ALL EMPLOYEE IN EMPLOYEE DTO TYPE
     * @return List<EmployeeDTO>
     */
    @Query("SELECT new com.emp.management.dto.EmployeeDTO(e.id, e.firstname, e.lastname, e.email, e.dob, e.city) FROM Employee e")
    List<EmployeeDTO> getAllEmployeeInEmployeeDTOType();

    /**
     * USING JPQL QUERY TO GET EMPLOYEE BY CITY, EMAIL, FIRSTNAME, LASTNAME
     *
     * @param city
     * @param email
     * @param firstname
     * @param lastname
     * @param pageable
     * @return Page<EmployeeDTO>
     */
    @Query("SELECT new com.emp.management.dto.EmployeeDTO(e.id, e.firstname, e.lastname, e.email, e.dob, e.city)" +
            " FROM Employee e " +
            "WHERE " +
            "(:city IS NULL OR LOWER(e.city) LIKE LOWER(concat('%', :city, '%'))) " +
            "AND (:email IS NULL OR LOWER(e.email) LIKE LOWER(concat('%', :email, '%'))) " +
            "AND (:firstname IS NULL OR LOWER(e.firstname) LIKE LOWER(concat('%', :firstname, '%'))) " +
            "AND (:lastname IS NULL OR LOWER(e.lastname) LIKE LOWER(concat('%', :lastname, '%')))")
    Page<EmployeeDTO> getEmployeeList(String city, String email, String firstname, String lastname, Pageable pageable);

    /**
     * USING JPQL QUERY TO GET SUPERVISOR LIST BY ID
     * @param id
     * @param pageable
     * @return Page<SupervisorDTO>
     */
    @Query(" SELECT new com.emp.management.dto.SupervisorDTO(s.id, s.firstname, s.lastname, s.email, s.city,s.salary) " +
            "FROM Employee emp " +
            "JOIN Task t ON emp.id =t.id " +
            "JOIN SupervisorTaskDetail std ON t.id = std.task.id " +
            "JOIN Supervisor s ON s.id = std.supervisor.id " +
            "WHERE emp.id = ?1 ")
    Page<SupervisorDTO> getSupervisorList(Long id, Pageable pageable);
}
