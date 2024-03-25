package com.csit.millonairegame;


import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class Question {
    private final String questionText;
    private final List<String> options;
    private final String correctAnswer;
    private final int cashReward;


    public Question(String questionText, List<String> options, String correctAnswer, int cashReward) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.cashReward = cashReward;
    }
    public int getCashReward() {
        return cashReward;
    }
    // Getters
    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public static List<Question> jsonToQuestions(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Question>>(){}.getType();
        return gson.fromJson(json, listType);
    }
    public static String questionsToJson(List<Question> questions) {
        Gson gson = new Gson();
        return gson.toJson(questions);
    }


}
