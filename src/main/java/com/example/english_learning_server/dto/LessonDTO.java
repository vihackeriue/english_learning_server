package com.example.english_learning_server.dto;

import lombok.Data;

@Data
public class LessonDTO {
    private Integer lessonId;
    private String lessonName;
    private String content;
    private String attachments;
    private String level;
    private Integer courseId; // Để lưu thông tin course liên kết
}