package com.example.english_learning_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "course")
public class CourseEntity extends BaseEntity {

    @Column(name = "course_name",columnDefinition = "NVARCHAR(255)")
    private String courseName;

    @Column(name = "course_code", length = 20)
    private String courseCode;

    @Column(name = "max_quantity")
    private int max_quantity;

    @Column (name = "status", nullable = false)
    private int status;

    @OneToMany(mappedBy = "course")
    private List<UserCourseEntity> courses;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getMax_quantity() {
        return max_quantity;
    }

    public void setMax_quantity(int max_quantity) {
        this.max_quantity = max_quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<UserCourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<UserCourseEntity> courses) {
        this.courses = courses;
    }
}
