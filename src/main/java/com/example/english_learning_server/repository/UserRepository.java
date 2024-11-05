package com.example.english_learning_server.repository;

import com.example.english_learning_server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
