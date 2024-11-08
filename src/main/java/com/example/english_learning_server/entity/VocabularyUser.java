package com.example.english_learning_server.entity;

import com.example.english_learning_server.entity.ids.VocabularyUserId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "VocabularyUser")
public class VocabularyUser {
    @EmbeddedId
    private VocabularyUserId id = new VocabularyUserId();

    @ManyToOne
    @MapsId("userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId("topicId")
    @JsonIgnore
    private Topic topic;

    @ManyToOne
    @MapsId("vocabId")
    @JsonIgnore
    private Vocabulary vocabulary;

    private String progress;

    // Getters and setters

    public VocabularyUser() {
    }

    public VocabularyUser(VocabularyUserId id, User user, Topic topic, Vocabulary vocabulary, String progress) {
        this.id = id;
        this.user = user;
        this.topic = topic;
        this.vocabulary = vocabulary;
        this.progress = progress;
    }

    public VocabularyUserId getId() {
        return id;
    }

    public void setId(VocabularyUserId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
