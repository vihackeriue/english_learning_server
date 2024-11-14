package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.UserLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLessonRepository extends JpaRepository<UserLesson, Long> {
    // Tìm tất cả UserLesson theo userId
    List<UserLesson> findByUserId(Integer userId);
}