package com.example.english_learning_server.controller;

import com.example.english_learning_server.entity.VocabularyUser;
import com.example.english_learning_server.service.VocabularyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vocabularyUser")
public class VocabularyUserController {

    @Autowired
    private VocabularyUserService vocabularyUserService;

    @PostMapping
    public VocabularyUser addVocabularyForUser(@RequestParam Integer userId,
                                               @RequestParam Integer vocabId,
                                               @RequestParam String progress) {
        return vocabularyUserService.saveVocabularyForUser(userId, vocabId, progress);
    }

    // Hiển thị tất cả VocabularyUser
    @GetMapping("/all")
    public List<VocabularyUser> getAllVocabularyUsers() {
        return vocabularyUserService.getAllVocabularyUsers();
    }

    // Hiển thị VocabularyUser theo id
    @GetMapping("/{id}")
    public VocabularyUser getVocabularyUserById(@PathVariable Long id) {
        return vocabularyUserService.getVocabularyUserById(id);
    }

    // Hiển thị VocabularyUser theo userId
    @GetMapping("/user/{userId}")
    public List<VocabularyUser> getVocabularyUsersByUserId(@PathVariable Integer userId) {
        return vocabularyUserService.getVocabularyUsersByUserId(userId);
    }
    // xoá vocabulary user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVocabularyUserById(@PathVariable Long id) {
        try {
            vocabularyUserService.deleteVocabularyUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body("VocabularyUser deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VocabularyUser not found");
        }
    }

}