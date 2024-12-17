package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.Test;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long> {
    Optional<UserTest> findByIdAndUserId(Long id, Integer userId);
}
