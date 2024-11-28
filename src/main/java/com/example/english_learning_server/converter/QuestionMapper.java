package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.QuestionDTO;
import com.example.english_learning_server.entity.Question;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    public QuestionDTO toDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setContent(question.getContent());
        dto.setAttachments(question.getAttachments());
        dto.setType(question.getType());
        dto.setScore(question.getScore());
        dto.setAnswerOptions(question.getAnswerOptions().stream()
                .map(answerOption -> new AnswerOptionMapper().toDTO(answerOption))
                .collect(Collectors.toList()));
        return dto;
    }
}