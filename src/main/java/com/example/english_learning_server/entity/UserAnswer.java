package com.example.english_learning_server.entity;

import com.example.english_learning_server.entity.ids.UserAnswerId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "UserAnswer")
public class UserAnswer {
    @EmbeddedId
    private UserAnswerId userAnswerId = new UserAnswerId();

    @ManyToOne
    @MapsId("userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId("testId")
    @JsonIgnore
    private Test test;

    @ManyToOne
    @MapsId("questionId")
    @JsonIgnore
    private Question question;

    @ManyToOne
    @MapsId("optionId")
    @JsonIgnore
    private AnswerOption option;

    private String score;

    // Các constructor và phương thức getter/setter nếu cần

    public UserAnswer() {
    }

    public UserAnswer(UserAnswerId userAnswerId, User user, Test test, Question question, AnswerOption option, String score) {
        this.userAnswerId = userAnswerId;
        this.user = user;
        this.test = test;
        this.question = question;
        this.option = option;
        this.score = score;
    }

    public UserAnswerId getUserAnswerId() {
        return userAnswerId;
    }

    public void setUserAnswerId(UserAnswerId userAnswerId) {
        this.userAnswerId = userAnswerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AnswerOption getOption() {
        return option;
    }

    public void setOption(AnswerOption option) {
        this.option = option;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
