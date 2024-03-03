package com.emp.management.repository;

import com.emp.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * CHECK IF USER EXISTS BY EMAIL
     * @param email
     * @return boolean
     */
    Boolean existsByEmail(String email);

    /**
     * FIND USER BY EMAIL
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);
}
