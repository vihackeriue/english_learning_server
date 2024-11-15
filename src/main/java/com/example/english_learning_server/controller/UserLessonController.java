package com.example.english_learning_server.controller;

import com.example.english_learning_server.entity.UserLesson;
import com.example.english_learning_server.service.UserLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userLesson")
public class UserLessonController {

    @Autowired
    private UserLessonService userLessonService;

    // API để người dùng bắt đầu học một bài học
    @PostMapping("/startLesson")
    public ResponseEntity<UserLesson> startLesson(@RequestParam Integer userId, @RequestParam Integer courseId, @RequestParam Integer lessonId) {
        UserLesson userLesson = userLessonService.startLesson(userId, courseId, lessonId);
        return ResponseEntity.ok(userLesson);
    }

    // API hiển thị danh sách tất cả UserLesson
    @GetMapping
    public ResponseEntity<List<UserLesson>> getAllUserLessons() {
        List<UserLesson> userLessons = userLessonService.getAllUserLessons();
        return ResponseEntity.ok(userLessons);
    }

    // API hiển thị danh sách UserLesson theo userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserLesson>> getUserLessonsByUserId(@PathVariable Integer userId) {
        List<UserLesson> userLessons = userLessonService.getUserLessonsByUserId(userId);
        return ResponseEntity.ok(userLessons);
    }

    // API xóa UserLesson theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserLesson(@PathVariable Long id) {
        userLessonService.deleteUserLesson(id);
        return ResponseEntity.noContent().build();
    }


}