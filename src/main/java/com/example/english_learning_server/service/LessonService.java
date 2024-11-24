package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.LessonMapper;
import com.example.english_learning_server.dto.LessonDTO;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.Lesson;
import com.example.english_learning_server.reponsitory.LessonRepository;
import com.example.english_learning_server.reponsitory.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonMapper lessonMapper;

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

    public List<Lesson> getLessonsByLevel(String level) {
        return lessonRepository.findByLevel(level);
    }
}