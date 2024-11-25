package com.example.english_learning_server.controller;


import com.example.english_learning_server.converter.CourseMapper;
import com.example.english_learning_server.dto.CourseDTO;
import com.example.english_learning_server.dto.UserCourseDTO;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courseDTOs = courseService.getAllCourses()
                .stream()
                .map(CourseMapper::toDTO)
                .toList();
        return new ResponseEntity<>(courseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Integer id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(value -> new ResponseEntity<>(CourseMapper.toDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody Course course) {
        Course newCourse = courseService.addCourse(course);
        return new ResponseEntity<>(CourseMapper.toDTO(newCourse), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Integer id, @RequestBody Course courseDetails) {
        Course updatedCourse = courseService.updateCourse(id, courseDetails);
        return updatedCourse != null
                ? new ResponseEntity<>(CourseMapper.toDTO(updatedCourse), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all-course-by-user")
    public ResponseEntity<List<UserCourseDTO>> getCoursesForCurrentUser() {
        List<UserCourseDTO> courses = courseService.getCoursesForCurrentUser();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}