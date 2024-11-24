package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.UserLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLessonRepository extends JpaRepository<UserLesson, Long> {
    // Tìm tất cả UserLesson theo userId
    List<UserLesson> findByUserId(Integer userId);
    // Cập nhật tiến trình (progress) theo id
    @Modifying
    @Query("UPDATE UserLesson u SET u.progress = :progress WHERE u.id = :id")
    void updateProgress(@Param("id") Long id, @Param("progress") Double progress);
}