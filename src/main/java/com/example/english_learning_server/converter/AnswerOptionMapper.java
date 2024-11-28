package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.AnswerOptionDTO;
import com.example.english_learning_server.entity.AnswerOption;
import org.springframework.stereotype.Component;

@Component
public class AnswerOptionMapper {

    public AnswerOptionDTO toDTO(AnswerOption answerOption) {
        AnswerOptionDTO dto = new AnswerOptionDTO();
        dto.setAnswerId(answerOption.getAnswerId());
        dto.setCorrectAnswer(answerOption.getCorrectAnswer());
        dto.setContent(answerOption.getContent());
        dto.setAttachments(answerOption.getAttachments());
        return dto;
    }
}