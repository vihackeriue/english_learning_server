package com.example.english_learning_server.entity;

import com.example.english_learning_server.entity.ids.UserCourseId;
import com.example.english_learning_server.user.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "UserCourse")
public class UserCourse {
    @EmbeddedId
    private UserCourseId id = new UserCourseId();

    @ManyToOne
    @MapsId("userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    private Integer studentCode;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Integer status;

    // Getters and setters

    public UserCourse() {
    }

    public UserCourse(UserCourseId id, User user, Course course, Integer studentCode, Role role, Integer status) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.studentCode = studentCode;
        this.role = role;
        this.status = status;
    }

    public UserCourseId getId() {
        return id;
    }

    public void setId(UserCourseId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(Integer studentCode) {
        this.studentCode = studentCode;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
