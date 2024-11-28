package com.example.english_learning_server.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TestDTO {
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
    private Integer courseId;
    private List<QuestionDTO> questions;
}
