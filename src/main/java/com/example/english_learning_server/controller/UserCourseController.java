package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.UserCourseDTO;
import com.example.english_learning_server.entity.UserCourse;
import com.example.english_learning_server.service.UserCourseService;
import com.example.english_learning_server.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userCourses")
public class UserCourseController {

    @Autowired
    private UserCourseService userCourseService;

    // Lấy UserCourses của user hiện tại
    @GetMapping("/me")
    public List<UserCourseDTO> getUserCoursesForCurrentUser() {
        return userCourseService.getUserCoursesForCurrentUser();
    }

    // Đăng ký học khóa học
    @PostMapping("/enroll")
    public UserCourseDTO enrollInCourse(@RequestParam Integer userId,
                                        @RequestParam Integer courseId,
                                        @RequestParam Integer studentCode,
                                        @RequestParam Role role) {
        return userCourseService.enrollInCourse(userId, courseId, studentCode, role);
    }

    // Lấy tất cả UserCourses
    @GetMapping
    public List<UserCourseDTO> getAllUserCourses() {
        return userCourseService.getAllUserCourses();
    }

    // Lấy UserCourse theo id
    @GetMapping("/{id}")
    public UserCourseDTO getUserCourseById(@PathVariable Long id) {
        return userCourseService.getUserCourseById(id);
    }

    // Lấy danh sách UserCourses theo userId
    @GetMapping("/user/{userId}")
    public List<UserCourseDTO> getUserCoursesByUserId(@PathVariable Integer userId) {
        return userCourseService.getUserCoursesByUserId(userId);
    }

    // Xóa UserCourse theo id
    @DeleteMapping("/{id}")
    public void deleteUserCourse(@PathVariable Long id) {
        userCourseService.deleteUserCourse(id);
    }
}
