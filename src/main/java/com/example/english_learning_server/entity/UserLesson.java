package com.example.english_learning_server.entity;

import com.example.english_learning_server.entity.ids.UserLessonId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_lession")
@JsonIgnoreProperties({"user", "course", "lesson"}) // Thêm annotation để tránh xuất ra vòng lặp
public class UserLesson {

    @EmbeddedId
    private UserLessonId userLessonId = new UserLessonId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"userLessons"}) // Không tuần tự hóa lại UserLessons của User để tránh vòng lặp
    private User user;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties({"userLessons"}) // Không tuần tự hóa lại UserLessons của Course để tránh vòng lặp
    private Course course;

    @ManyToOne
    @MapsId("lessonId")
    @JoinColumn(name = "lesson_id")
    @JsonIgnoreProperties({"userLessons"}) // Không tuần tự hóa lại UserLessons của Lesson để tránh vòng lặp
    private Lesson lesson;

    @Column(name = "Progress")
    private String progress;

    @Column(name = "status")
    private Integer status;

    public UserLesson() {
    }

    public UserLesson(UserLessonId userLessonId, User user, Course course, Lesson lesson, String progress, Integer status) {
        this.userLessonId = userLessonId;
        this.user = user;
        this.course = course;
        this.lesson = lesson;
        this.progress = progress;
        this.status = status;
    }

    public UserLessonId getUserLessonId() {
        return userLessonId;
    }

    public void setUserLessonId(UserLessonId userLessonId) {
        this.userLessonId = userLessonId;
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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
