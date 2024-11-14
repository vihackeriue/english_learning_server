package com.example.english_learning_server.controller;

import com.example.english_learning_server.entity.UserCourse;
import com.example.english_learning_server.service.UserCourseService;
import com.example.english_learning_server.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/userCourses")
public class UserCourseController {

    @Autowired
    private UserCourseService userCourseService;

    @PostMapping("/enroll")
    public UserCourse enrollInCourse(@RequestParam Integer userId,
                                     @RequestParam Integer courseId,
                                     @RequestParam Integer studentCode,
                                     @RequestParam Role role) {
        return userCourseService.enrollInCourse(userId, courseId, studentCode, role);
    }

    // Lấy tất cả UserCourses
    @GetMapping
    public List<UserCourse> getAllUserCourses() {
        return userCourseService.getAllUserCourses();
    }

    // Lấy UserCourse theo id
    @GetMapping("/{id}")
    public UserCourse getUserCourseById(@PathVariable Long id) {
        return userCourseService.getUserCourseById(id);
    }

    // Lấy danh sách UserCourses theo userId
    @GetMapping("/user/{userId}")
    public List<UserCourse> getUserCoursesByUserId(@PathVariable Integer userId) {
        return userCourseService.getUserCoursesByUserId(userId);
    }

    // Xóa UserCourse theo id
    @DeleteMapping("/{id}")
    public void deleteUserCourse(@PathVariable Long id) {
        userCourseService.deleteUserCourse(id);
    }
}
