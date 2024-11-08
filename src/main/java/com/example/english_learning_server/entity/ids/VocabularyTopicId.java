package com.example.english_learning_server.entity.ids;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VocabularyTopicId implements Serializable {

    private Integer topicId;
    private Integer vocabId;

    public VocabularyTopicId() {
    }

    public VocabularyTopicId(Integer topicId, Integer vocabId) {
        this.topicId = topicId;
        this.vocabId = vocabId;
    }

    // Getters and Setters
    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getVocabId() {
        return vocabId;
    }

    public void setVocabId(Integer vocabId) {
        this.vocabId = vocabId;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VocabularyTopicId that = (VocabularyTopicId) o;
        return Objects.equals(topicId, that.topicId) &&
                Objects.equals(vocabId, that.vocabId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId, vocabId);
    }
}
