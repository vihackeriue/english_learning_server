package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.UserLessonMapper;
import com.example.english_learning_server.dto.UserLessonDTO;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.Lesson;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.UserLesson;
import com.example.english_learning_server.reponsitory.CourseRepository;
import com.example.english_learning_server.reponsitory.LessonRepository;
import com.example.english_learning_server.reponsitory.UserLessonRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    @Autowired
    private UserLessonMapper userLessonMapper;

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

//    // Lấy danh sách UserLessons của user hiện tại
//    public List<UserLessonDTO> getUserLessonsForCurrentUser() {
//        // Lấy email của user từ SecurityContext
//        String userEmail = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            userEmail = ((UserDetails) principal).getUsername();
//        } else {
//            userEmail = principal.toString();
//        }
//
//        // Tìm user hiện tại
//        User user = userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Lấy danh sách UserLesson của user và chuyển đổi sang DTO
//        List<UserLesson> userLessons = userLessonRepository.findByUserId(user.getId());
//        return userLessons.stream()
//                .map(userLessonMapper::toDTO)
//                .collect(Collectors.toList());
//    }

    // Lấy danh sách UserLessons của user hiện tại và sắp xếp theo level
    public List<UserLessonDTO> getUserLessonsForCurrentUser() {
        // Lấy email của user từ SecurityContext
        String userEmail = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }

        // Tìm user hiện tại
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy danh sách UserLesson của user
        List<UserLesson> userLessons = userLessonRepository.findByUserId(user.getId());

        // Chuyển đổi danh sách UserLesson sang UserLessonDTO và sắp xếp theo level
        return userLessons.stream()
                .map(userLesson -> {
                    UserLessonDTO dto = new UserLessonDTO();
                    dto.setLessonId(userLesson.getLesson().getLessonId());
                    dto.setLessonName(userLesson.getLesson().getLessonName());
                    dto.setContent(userLesson.getLesson().getContent());
                    dto.setAttachments(userLesson.getLesson().getAttachments());
                    dto.setLevel(userLesson.getLesson().getLevel());
                    dto.setCourseId(userLesson.getCourse().getCourseId());

                    // Kiểm tra nếu progress là null thì gán là 0
                    Double progress = userLesson.getProgress();
                    dto.setProgress(progress != null ? progress : 0.0);  // Nếu progress null thì gán 0.0

                    return dto;
                })
                .sorted(Comparator.comparing(UserLessonDTO::getLevel))  // Sắp xếp theo level
                .collect(Collectors.toList());
    }


    // bắt đầu một bài học
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
        userLesson.setProgress(1.0); // Trạng thái tiến trình ban đầu
        userLesson.setStatus(1); // Trạng thái khóa học mở (có thể là 1 cho "đang học")

        // Lưu vào bảng UserLesson
        return userLessonRepository.save(userLesson);
    }


    // Cập nhật UserLesson
    public UserLessonDTO updateUserLesson(Long id, UserLessonDTO userLessonDTO) {
        // Tìm UserLesson theo ID
        UserLesson existingUserLesson = userLessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserLesson not found"));

        // Cập nhật các trường thông tin
        if (userLessonDTO.getProgress() > 0) {
            existingUserLesson.setProgress(userLessonDTO.getProgress());
        }

        // Lưu lại thay đổi vào database
        UserLesson updatedUserLesson = userLessonRepository.save(existingUserLesson);

        // Trả về UserLessonDTO đã được cập nhật
        return userLessonMapper.toDTO(updatedUserLesson);
    }


}