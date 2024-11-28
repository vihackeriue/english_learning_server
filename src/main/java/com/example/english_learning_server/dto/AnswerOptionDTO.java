package com.example.english_learning_server.dto;

import lombok.Data;

@Data
public class AnswerOptionDTO {
    private Integer answerId;
    private String correctAnswer;
    private String content;
    private String attachments;
}
