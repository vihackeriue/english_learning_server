package com.example.english_learning_server.service;

import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.reponsitory.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Integer id) {
        return courseRepository.findById(id);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Integer id, Course courseDetails) {
        return courseRepository.findById(id).map(course -> {
            course.setCourseName(courseDetails.getCourseName());
            course.setCourseCode(courseDetails.getCourseCode());
            course.setMaxQuantity(courseDetails.getMaxQuantity());
            course.setStatusCourse(courseDetails.getStatusCourse());
            course.setImage(courseDetails.getImage());
            return courseRepository.save(course);
        }).orElse(null);
    }

    public boolean deleteCourse(Integer id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.delete(course);
            return true;
        }).orElse(false);
    }
}