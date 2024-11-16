package com.example.english_learning_server.service;

import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReponsitory userRepository;

    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy người dùng theo ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User getCurrentUser() {
        String currentEmail = getCurrentUserEmail();
        return userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}