package com.example.english_learning_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "Topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicId;

    private String topicName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_create_id")
    @JsonIgnore
    private User userCreate;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VocabularyTopic> vocabularyTopics = new ArrayList<>();

    // Getters and setters

    public Topic() {
    }

    public Topic(Integer topicId, String topicName, String description, User userCreate, List<VocabularyTopic> vocabularyTopics) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.description = description;
        this.userCreate = userCreate;
        this.vocabularyTopics = vocabularyTopics;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(User userCreate) {
        this.userCreate = userCreate;
    }

    public List<VocabularyTopic> getVocabularyTopics() {
        return vocabularyTopics;
    }

    public void setVocabularyTopics(List<VocabularyTopic> vocabularyTopics) {
        this.vocabularyTopics = vocabularyTopics;
    }
}
