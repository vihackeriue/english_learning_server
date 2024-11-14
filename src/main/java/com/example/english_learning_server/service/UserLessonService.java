package com.example.english_learning_server.service;

import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.Lesson;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.UserLesson;
import com.example.english_learning_server.reponsitory.CourseRepository;
import com.example.english_learning_server.reponsitory.LessonRepository;
import com.example.english_learning_server.reponsitory.UserLessonRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLessonService {

    @Autowired
    private UserLessonRepository userLessonRepository;

    @Autowired
    private UserReponsitory userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public UserLesson startLesson(Integer userId, Integer courseId, Integer lessonId) {
        // Tìm User, Course và Lesson theo ID
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found"));

        // Tạo một đối tượng UserLesson mới và lưu vào bảng
        UserLesson userLesson = new UserLesson();
        userLesson.setUser(user);
        userLesson.setCourse(course);
        userLesson.setLesson(lesson);
        userLesson.setProgress("0%"); // Trạng thái tiến trình ban đầu
        userLesson.setStatus(1); // Trạng thái khóa học mở (có thể là 1 cho "đang học")

        // Lưu vào bảng UserLesson
        return userLessonRepository.save(userLesson);
    }
    // Lấy danh sách tất cả UserLesson
    public List<UserLesson> getAllUserLessons() {
        return userLessonRepository.findAll();
    }

    // Lấy danh sách UserLesson theo userId
    public List<UserLesson> getUserLessonsByUserId(Integer userId) {
        return userLessonRepository.findByUserId(userId);
    }

    // Xóa UserLesson theo id
    public void deleteUserLesson(Long id) {
        Optional<UserLesson> userLesson = userLessonRepository.findById(id);
        if (userLesson.isPresent()) {
            userLessonRepository.deleteById(id);
        } else {
            throw new RuntimeException("UserLesson not found with id " + id);
        }
    }

}