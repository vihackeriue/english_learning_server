package com.example.english_learning_server.controller;

import com.example.english_learning_server.entity.Vocabulary;
import com.example.english_learning_server.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth/vocabularies")
public class VocabularyController {

    @Autowired
    private VocabularyService vocabularyService;

    // API thêm Vocabulary

    // API thêm Vocabulary với Map<String, Object>
    @PostMapping
    public ResponseEntity<Vocabulary> addVocabulary(@RequestBody Map<String, Object> vocabularyData) {
        // Lấy lessonId và các thuộc tính của Vocabulary từ map
        Integer lessonId = (Integer) vocabularyData.get("lessonId");
        String word = (String) vocabularyData.get("word");
        String meaning = (String) vocabularyData.get("meaning");
        String description = (String) vocabularyData.get("description");
        String image = (String) vocabularyData.get("image");
        String audio = (String) vocabularyData.get("audio");

        // Tạo đối tượng Vocabulary và set các thuộc tính
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setWord(word);
        vocabulary.setMeaning(meaning);
        vocabulary.setDescription(description);
        vocabulary.setImage(image);
        vocabulary.setAudio(audio);

        // Thêm Vocabulary vào lesson tương ứng
        Vocabulary newVocabulary = vocabularyService.addVocabularyToLesson(vocabulary, lessonId);
        return new ResponseEntity<>(newVocabulary, HttpStatus.CREATED);
    }


    // API hiển thị danh sách tất cả Vocabulary
    @GetMapping
    public ResponseEntity<List<Vocabulary>> getAllVocabularies() {
        List<Vocabulary> vocabularies = vocabularyService.getAllVocabularies();
        return ResponseEntity.ok(vocabularies);
    }

    // API hiển thị Vocabulary theo vocabId
    @GetMapping("/{vocabId}")
    public ResponseEntity<Vocabulary> getVocabularyById(@PathVariable Integer vocabId) {
        Vocabulary vocabulary = vocabularyService.getVocabularyById(vocabId);
        return ResponseEntity.ok(vocabulary);
    }

    // API hiển thị Vocabulary theo word
    @GetMapping("/word/{word}")
    public ResponseEntity<Vocabulary> getVocabularyByWord(@PathVariable String word) {
        Vocabulary vocabulary = vocabularyService.getVocabularyByWord(word);
        return ResponseEntity.ok(vocabulary);
    }

    // API hiển thị Vocabulary theo lesson_id
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<Vocabulary>> getVocabulariesByLessonId(@PathVariable Integer lessonId) {
        List<Vocabulary> vocabularies = vocabularyService.getVocabulariesByLessonId(lessonId);
        return ResponseEntity.ok(vocabularies);
    }

    // API cập nhật Vocabulary theo ID
    @PutMapping("/{vocabId}")
    public ResponseEntity<Vocabulary> updateVocabulary(
            @PathVariable Integer vocabId,
            @RequestBody Vocabulary updatedVocabulary) {
        Vocabulary vocabulary = vocabularyService.updateVocabulary(vocabId, updatedVocabulary);
        return ResponseEntity.ok(vocabulary);
    }

    // API xóa Vocabulary theo ID
    @DeleteMapping("/{vocabId}")
    public ResponseEntity<Void> deleteVocabulary(@PathVariable Integer vocabId) {
        vocabularyService.deleteVocabulary(vocabId);
        return ResponseEntity.noContent().build();
    }

}