package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.LessonMapper;
import com.example.english_learning_server.dto.LessonDTO;
import com.example.english_learning_server.dto.UserLessonDTO;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.Lesson;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.reponsitory.LessonRepository;
import com.example.english_learning_server.reponsitory.CourseRepository;
import com.example.english_learning_server.reponsitory.UserLessonRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private UserReponsitory userRepository;

    @Autowired
    private UserLessonRepository userLessonRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(Integer lessonId) {
        return lessonRepository.findById(lessonId);
    }

    public Lesson addLesson(LessonDTO lessonDTO) {
        Course course = courseRepository.findById(lessonDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + lessonDTO.getCourseId()));
        Lesson lesson = lessonMapper.toEntity(lessonDTO, course);
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Integer lessonId, LessonDTO lessonDTO) {
        return lessonRepository.findById(lessonId).map(lesson -> {
            Course course = courseRepository.findById(lessonDTO.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + lessonDTO.getCourseId()));
            lesson = lessonMapper.toEntity(lessonDTO, course);
            lesson.setLessonId(lessonId);
            return lessonRepository.save(lesson);
        }).orElse(null);
    }

    public boolean deleteLesson(Integer lessonId) {
        return lessonRepository.findById(lessonId).map(lesson -> {
            lessonRepository.delete(lesson);
            return true;
        }).orElse(false);
    }

    public List<Lesson> getLessonsByCourseId(Integer courseId) {
        return lessonRepository.findByCourseCourseId(courseId);
    }

    public List<Lesson> getLessonsByLevel(int level) {
        return lessonRepository.findByLevel(level);
    }

    public List<UserLessonDTO> getUserLessonsForCurrentUser() {
        // Lấy email của user từ SecurityContext
        String userEmail = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }

        // Tìm user hiện tại từ email
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy danh sách bài học từ UserLessonRepository
        List<Object[]> results = userLessonRepository.findLessonsByUserId(user.getId());

        // Chuyển đổi kết quả thành UserLessonDTO
        List<UserLessonDTO> userLessons = results.stream()
                .map(result -> {
                    UserLessonDTO dto = new UserLessonDTO();
                    dto.setLessonId((Integer) result[0]);
                    dto.setLessonName((String) result[1]);
                    dto.setContent((String) result[2]);
                    dto.setAttachments((String) result[3]);
                    dto.setLevel((Integer) result[4]);
                    dto.setCourseId((Integer) result[5]);

                    // Kiểm tra nếu progress là null thì gán là 0
                    dto.setProgress(result[6] != null ? (Double) result[6] : 0.0);  // Nếu progress null thì gán 0.0

                    return dto;
                })
                .sorted(Comparator.comparing(UserLessonDTO::getLevel))  // Sắp xếp theo level
                .collect(Collectors.toList());

        return userLessons;
    }


}