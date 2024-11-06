package com.example.english_learning_server.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReponsitory extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
