package com.example.english_learning_server.entity;

import com.example.english_learning_server.entity.ids.UserTestId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@Entity
@Table(name = "UserTest")
public class UserTest {
    @EmbeddedId
    private UserTestId id = new UserTestId();

    @ManyToOne
    @MapsId("userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId("testId")
    private Test test;

    private LocalTime examTimes;
    private Integer status;
    private String score;
    private String answerAttachments;

    // Getters and setters


    public UserTest() {
    }

    public UserTest(UserTestId id, User user, Test test, LocalTime examTimes, Integer status, String score, String answerAttachments) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.examTimes = examTimes;
        this.status = status;
        this.score = score;
        this.answerAttachments = answerAttachments;
    }

    public UserTestId getId() {
        return id;
    }

    public void setId(UserTestId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public LocalTime getExamTimes() {
        return examTimes;
    }

    public void setExamTimes(LocalTime examTimes) {
        this.examTimes = examTimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAnswerAttachments() {
        return answerAttachments;
    }

    public void setAnswerAttachments(String answerAttachments) {
        this.answerAttachments = answerAttachments;
    }
}
