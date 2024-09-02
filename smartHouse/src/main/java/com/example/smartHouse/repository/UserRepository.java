package com.example.smartHouse.repository;

import com.example.smartHouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
