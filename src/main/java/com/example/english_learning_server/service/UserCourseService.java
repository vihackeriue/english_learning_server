package com.example.english_learning_server.service;

import com.example.english_learning_server.entity.UserCourse;
import com.example.english_learning_server.reponsitory.CourseRepository;
import com.example.english_learning_server.reponsitory.UserCourseRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseService {

    @Autowired
    private UserReponsitory userRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Logic cho việc đăng ký khóa học
    public UserCourse enrollInCourse(Integer userId, Integer courseId, Integer studentCode, Role role) {
        // Kiểm tra sự tồn tại của người dùng và khóa học
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        // Tạo đối tượng UserCourse mới
        UserCourse userCourse = UserCourse.builder()
                .user(user)
                .course(course)
                .studentCode(studentCode)
                .role(role)
                .status(1) // trạng thái mặc định là 1, có thể thay đổi tùy theo logic
                .build();

        // Lưu đối tượng UserCourse vào cơ sở dữ liệu
        return userCourseRepository.save(userCourse);
    }

    // Lấy tất cả UserCourses
    public List<UserCourse> getAllUserCourses() {
        return userCourseRepository.findAll();
    }

    // Lấy UserCourse theo id
    public UserCourse getUserCourseById(Long id) {
        return userCourseRepository.findById(id).orElseThrow(() -> new RuntimeException("UserCourse not found"));
    }

    // Lấy danh sách UserCourses theo userId
    public List<UserCourse> getUserCoursesByUserId(Integer userId) {
        return userCourseRepository.findByUserId(userId);
    }

    // Xóa UserCourse theo id
    public void deleteUserCourse(Long id) {
        UserCourse userCourse = userCourseRepository.findById(id).orElseThrow(() -> new RuntimeException("UserCourse not found"));
        userCourseRepository.delete(userCourse);
    }
}
