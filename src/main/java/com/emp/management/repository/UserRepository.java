package com.emp.management.repository;

import com.emp.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    //FIND USER BY EMAIL
    Optional<User> findByEmail(String email);
}
