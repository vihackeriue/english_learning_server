package com.example.english_learning_server.entity;

import com.example.english_learning_server.entity.ids.VocabularyTopicId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "VocabularyTopic")
public class VocabularyTopic {
    @EmbeddedId
    private VocabularyTopicId id = new VocabularyTopicId();

    @ManyToOne
    @MapsId("topicId")
    @JsonIgnore
    private Topic topic;

    @ManyToOne
    @MapsId("vocabId")
    @JsonIgnore
    private Vocabulary vocabulary;

    // Getters and setters

    public VocabularyTopic() {
    }

    public VocabularyTopic(VocabularyTopicId id, Topic topic, Vocabulary vocabulary) {
        this.id = id;
        this.topic = topic;
        this.vocabulary = vocabulary;
    }

    public VocabularyTopicId getId() {
        return id;
    }

    public void setId(VocabularyTopicId id) {
        this.id = id;
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
}
