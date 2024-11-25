package com.example.english_learning_server.controller;

import com.example.english_learning_server.converter.UserLessonMapper;
import com.example.english_learning_server.dto.UserLessonDTO;
import com.example.english_learning_server.entity.UserLesson;
import com.example.english_learning_server.service.UserLessonService;
import com.example.english_learning_server.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/user-Lesson")
public class UserLessonController {

    @Autowired
    private UserLessonService userLessonService;

    @Autowired
    private UserLessonMapper userLessonMapper;

    // API để người dùng bắt đầu học một bài học

    // API để người dùng bắt đầu học một bài học
    @PostMapping("/start-Lesson")
    public ResponseEntity<UserLesson> startLesson(@RequestBody Map<String, Object> request) {
        // Lấy các tham số từ body JSON
        Integer userId = (Integer) request.get("userId");
        Integer courseId = (Integer) request.get("courseId");
        Integer lessonId = (Integer) request.get("lessonId");

        // Gọi service để bắt đầu bài học
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

    // Lấy UserLessons của user hiện tại
//    @GetMapping("/me")
//    public List<UserLessonDTO> getUserLessonsForCurrentUser() {
//        return userLessonService.getUserLessonsForCurrentUser();
//    }

    // Lấy danh sách bài học của người dùng hiện tại
    @GetMapping("/user")
    public ResponseEntity<List<UserLessonDTO>> getUserLessonsForCurrentUser() {
        List<UserLessonDTO> lessons = userLessonService.getUserLessonsForCurrentUser();
        return ResponseEntity.ok(lessons);
    }

    // API xóa UserLesson theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserLesson(@PathVariable Long id) {
        userLessonService.deleteUserLesson(id);
        return ResponseEntity.noContent().build();
    }

    // Cập nhật UserLesson
    @PutMapping("/{id}")
    public UserLessonDTO updateUserLesson(@PathVariable Long id, @RequestBody UserLessonDTO userLessonDTO) {
        return userLessonService.updateUserLesson(id, userLessonDTO);
    }


}