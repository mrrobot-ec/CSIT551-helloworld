package com.csit.quizgame;

import java.util.List;

public class QuizQuestion {

    private String title;
    private List<String> options;
    private String correctAnswer;

    public QuizQuestion(String title, List<String> options, String correctAnswer) {
        this.title = title;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}