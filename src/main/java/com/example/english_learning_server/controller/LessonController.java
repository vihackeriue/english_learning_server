package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.LessonDTO;
import com.example.english_learning_server.converter.LessonMapper;
import com.example.english_learning_server.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonMapper lessonMapper;

    @PostMapping
    public ResponseEntity<LessonDTO> addLesson(@RequestBody LessonDTO lessonDTO) {
        LessonDTO savedLesson = lessonMapper.toDTO(
                lessonService.addLesson(lessonDTO)
        );
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LessonDTO>> getAllLessons() {
        List<LessonDTO> lessons = lessonService.getAllLessons().stream()
                .map(lessonMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Integer lessonId) {
        return lessonService.getLessonById(lessonId)
                .map(lesson -> new ResponseEntity<>(lessonMapper.toDTO(lesson), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<LessonDTO> updateLesson(@PathVariable Integer lessonId, @RequestBody LessonDTO lessonDTO) {
        LessonDTO updatedLesson = lessonMapper.toDTO(
                lessonService.updateLesson(lessonId, lessonDTO)
        );
        return updatedLesson != null ? new ResponseEntity<>(updatedLesson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Integer lessonId) {
        boolean isDeleted = lessonService.deleteLesson(lessonId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<LessonDTO>> getLessonsByCourseId(@PathVariable Integer courseId) {
        List<LessonDTO> lessons = lessonService.getLessonsByCourseId(courseId).stream()
                .map(lessonMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<LessonDTO>> getLessonsByLevel(@PathVariable String level) {
        List<LessonDTO> lessons = lessonService.getLessonsByLevel(level).stream()
                .map(lessonMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }
}