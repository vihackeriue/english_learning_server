package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.TestDTO;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.Test;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {

    public TestDTO toDTO(Test test) {
        TestDTO dto = new TestDTO();
        dto.setTestId(test.getTestId());
        dto.setTestName(test.getTestName());
        dto.setDescription(test.getDescription());
        dto.setExamTime(test.getExamTime());
        dto.setExamDate(test.getExamDate());
        dto.setType(test.getType());
        dto.setMaxNumberOfExams(test.getMaxNumberOfExams());
        dto.setLevel(test.getLevel());
        dto.setPassingScore(test.getPassingScore());
        dto.setStatus(test.getStatus());
        dto.setCourseId(test.getCourse() != null ? test.getCourse().getCourseId() : null);
        // Questions mapping có thể thêm nếu cần
        return dto;
    }

    public Test toEntity(TestDTO dto) {
        Test test = new Test();
        test.setTestId(dto.getTestId());
        test.setTestName(dto.getTestName());
        test.setDescription(dto.getDescription());
        test.setExamTime(dto.getExamTime());
        test.setExamDate(dto.getExamDate());
        test.setType(dto.getType());
        test.setMaxNumberOfExams(dto.getMaxNumberOfExams());
        test.setLevel(dto.getLevel());
        test.setPassingScore(dto.getPassingScore());
        test.setStatus(dto.getStatus());

        // Nếu cần ánh xạ Course
        if (dto.getCourseId() != null) {
            Course course = new Course(); // Giả sử bạn có thực thể Course
            course.setCourseId(dto.getCourseId());
            test.setCourse(course);
        }

        // Questions mapping có thể thêm nếu cần
        return test;
    }
}