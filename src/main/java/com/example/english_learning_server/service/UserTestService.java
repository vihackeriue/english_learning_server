package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.UserTestConverter;
import com.example.english_learning_server.dto.UserTestDTO;
import com.example.english_learning_server.entity.Test;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.UserTest;
import com.example.english_learning_server.reponsitory.TestRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import com.example.english_learning_server.reponsitory.UserTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class UserTestService {
    @Autowired
    private UserTestRepository userTestRepository;

    @Autowired
    private UserReponsitory userRepository;

    @Autowired
    private TestRepository testRepository;
    public UserTestDTO createOrUpdateUserTest(UserTestDTO dto) {
        // Lấy userId từ access_token (dùng SecurityContextHolder)
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy Test từ ID
        Test test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new RuntimeException("Test not found"));

        // Kiểm tra xem dữ liệu đã tồn tại chưa dựa trên userId và testId
        Optional<UserTest> existingUserTest = userTestRepository.findByIdAndUserId(dto.getId(), user.getId());

        UserTest userTest;
        if (existingUserTest.isPresent()) {
            // Cập nhật dữ liệu chỉ với điểm số, ngày giờ
            userTest = existingUserTest.get();
            userTest.setScore(dto.getScore());  // Cập nhật điểm số
            userTest.setExamTimes(LocalTime.now()); // Cập nhật thời gian làm bài
            userTest.setDate(LocalDate.now()); // Cập nhật ngày làm bài
        } else {
            // Thêm mới dữ liệu với ngày giờ hiện tại và điểm số từ DTO
            userTest = UserTestConverter.dtoToEntity(dto, user, test);
            userTest.setExamTimes(LocalTime.now()); // Cập nhật thời gian làm bài
            userTest.setDate(LocalDate.now()); // Cập nhật ngày làm bài
        }

        // Lưu vào cơ sở dữ liệu
        UserTest savedUserTest = userTestRepository.save(userTest);

        // Trả về DTO sau khi lưu
        return UserTestConverter.entityToDTO(savedUserTest);
    }


}
