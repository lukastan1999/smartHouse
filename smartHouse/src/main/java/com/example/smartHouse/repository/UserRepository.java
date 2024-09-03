package com.example.smartHouse.repository;

import com.example.smartHouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByActivationToken(String activationToken);
    Optional<User> findByEmail(String email);

}
