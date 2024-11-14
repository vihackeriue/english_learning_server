package com.example.english_learning_server.service;

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

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(Integer lessonId) {
        return lessonRepository.findById(lessonId);
    }

    public Lesson addLesson(Lesson lesson, Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Integer lessonId, Lesson lessonDetails) {
        return lessonRepository.findById(lessonId).map(lesson -> {
            lesson.setLessonName(lessonDetails.getLessonName());
            lesson.setContent(lessonDetails.getContent());
            lesson.setAttachments(lessonDetails.getAttachments());
            lesson.setLevel(lessonDetails.getLevel());
            lesson.setCourse(lessonDetails.getCourse());
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