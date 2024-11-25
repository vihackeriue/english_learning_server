package com.example.english_learning_server.controller;

import com.example.english_learning_server.converter.UserLessonMapper;
import com.example.english_learning_server.dto.UserLessonDTO;
import com.example.english_learning_server.entity.UserLesson;
import com.example.english_learning_server.reponsitory.UserLessonRepository;
import com.example.english_learning_server.service.UserLessonService;
import com.example.english_learning_server.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/user-Lesson")
public class UserLessonController {

    @Autowired
    private UserLessonService userLessonService;

    @Autowired
    private UserLessonMapper userLessonMapper;

    @Autowired
    private UserLessonRepository userLessonRepository;



    // API hiển thị danh sách tất cả UserLesson
    @GetMapping
    public ResponseEntity<List<UserLesson>> getAllUserLessons() {
        List<UserLesson> userLessons = userLessonService.getAllUserLessons();
        return ResponseEntity.ok(userLessons);
    }



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



    @PostMapping("/start-or-update-Lesson")
    public ResponseEntity<UserLesson> startOrUpdateLesson(@RequestBody Map<String, Object> request) {
        // Lấy các tham số từ body JSON
        Integer userId = (Integer) request.get("userId");
        Integer courseId = (Integer) request.get("courseId");
        Integer lessonId = (Integer) request.get("lessonId");
        Double progress = ((Number) request.get("progress")).doubleValue();  // Lấy thông tin tiến trình (nếu có)

        // Kiểm tra xem người dùng đã có UserLesson cho bài học này chưa
        List<UserLesson> userLessons = userLessonService.getUserLessonsByUserId(userId);
        Optional<UserLesson> existingUserLesson = userLessons.stream()
                .filter(ul -> ul.getCourse().getCourseId().equals(courseId) && ul.getLesson().getLessonId().equals(lessonId))
                .findFirst();

        if (existingUserLesson.isPresent()) {
            // Nếu UserLesson đã tồn tại, cập nhật tiến trình
            UserLesson userLesson = existingUserLesson.get();
            userLesson.setProgress(progress);
            UserLesson updatedUserLesson = userLessonRepository.save(userLesson);
            return ResponseEntity.ok(updatedUserLesson); // Trả về bài học đã được cập nhật
        } else {
            // Nếu UserLesson chưa tồn tại, tạo mới một UserLesson
            UserLesson userLesson = userLessonService.startLesson(userId, courseId, lessonId);
            return ResponseEntity.ok(userLesson); // Trả về bài học mới được tạo
        }
    }


}