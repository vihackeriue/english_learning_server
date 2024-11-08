package com.example.english_learning_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "Test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;

    private String testName;
    private String description;
    private LocalTime examTime;
    private LocalDate examDate;
    private String type;
    private String maxNumberOfExams;
    private String level;
    private String passingScore;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserTest> userTests = new ArrayList<>();

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    // Getters and setters

    public Test() {
    }

    public Test(Integer testId, String testName, String description, LocalTime examTime, LocalDate examDate, String type, String maxNumberOfExams, String level, String passingScore, Integer status, Course course, List<UserTest> userTests, List<Question> questions) {
        this.testId = testId;
        this.testName = testName;
        this.description = description;
        this.examTime = examTime;
        this.examDate = examDate;
        this.type = type;
        this.maxNumberOfExams = maxNumberOfExams;
        this.level = level;
        this.passingScore = passingScore;
        this.status = status;
        this.course = course;
        this.userTests = userTests;
        this.questions = questions;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getExamTime() {
        return examTime;
    }

    public void setExamTime(LocalTime examTime) {
        this.examTime = examTime;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaxNumberOfExams() {
        return maxNumberOfExams;
    }

    public void setMaxNumberOfExams(String maxNumberOfExams) {
        this.maxNumberOfExams = maxNumberOfExams;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(String passingScore) {
        this.passingScore = passingScore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<UserTest> getUserTests() {
        return userTests;
    }

    public void setUserTests(List<UserTest> userTests) {
        this.userTests = userTests;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}