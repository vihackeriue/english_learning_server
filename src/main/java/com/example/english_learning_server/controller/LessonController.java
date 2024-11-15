package com.example.english_learning_server.controller;

import com.example.english_learning_server.entity.Lesson;
import com.example.english_learning_server.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    // API thêm Lesson
    @PostMapping
    public ResponseEntity<Lesson> addLesson(@RequestBody Map<String, Object> lessonData) {
        Integer courseId = (Integer) lessonData.get("courseId");
        String lessonName = (String) lessonData.get("lessonName");
        String content = (String) lessonData.get("content");
        String attachments = (String) lessonData.get("attachments");
        String level = (String) lessonData.get("level");

        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonName);
        lesson.setContent(content);
        lesson.setAttachments(attachments);
        lesson.setLevel(level);

        Lesson newLesson = lessonService.addLesson(lesson, courseId);
        return new ResponseEntity<>(newLesson, HttpStatus.CREATED);
    }


    // API hiển thị tất cả Lessons
    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    // API hiển thị Lesson theo lessonId
    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Integer lessonId) {
        Optional<Lesson> lesson = lessonService.getLessonById(lessonId);
        return lesson.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // API cập nhật Lesson theo lessonId
    @PutMapping("/{lessonId}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Integer lessonId, @RequestBody Lesson lessonDetails) {
        Lesson updatedLesson = lessonService.updateLesson(lessonId, lessonDetails);
        return updatedLesson != null ? new ResponseEntity<>(updatedLesson, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // API xóa Lesson theo lessonId
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Integer lessonId) {
        boolean isDeleted = lessonService.deleteLesson(lessonId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // API hiển thị danh sách Lesson theo course_id
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Lesson>> getLessonsByCourseId(@PathVariable Integer courseId) {
        List<Lesson> lessons = lessonService.getLessonsByCourseId(courseId);
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    // API hiển thị danh sách Lesson theo level
    @GetMapping("/level/{level}")
    public ResponseEntity<List<Lesson>> getLessonsByLevel(@PathVariable String level) {
        List<Lesson> lessons = lessonService.getLessonsByLevel(level);
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }
}