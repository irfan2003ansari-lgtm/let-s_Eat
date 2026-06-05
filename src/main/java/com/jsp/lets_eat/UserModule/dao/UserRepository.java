package com.jsp.lets_eat.UserModule.dao;

import com.jsp.lets_eat.UserModule.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
