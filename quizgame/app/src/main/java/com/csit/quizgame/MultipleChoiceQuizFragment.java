package com.csit.quizgame;

import android.widget.CheckBox;

import java.util.List;

public class MultipleChoiceQuizFragment extends QuizFragment {

    @Override
    protected void setUpQuestionType() {
        // Implement specific logic for multiple-choice questions
        // Example: Set up multiple-choice options dynamically
        List<String> options = quizQuestions.get(getCurrentQuestionIndex()).getOptions();

        for (int i = 0; i < checkBoxes.size(); i++) {
            CheckBox checkBox = checkBoxes.get(i);
            checkBox.setText(options.get(i));
        }
    }

    @Override
    protected void onUserAnswerSelected() {
        // Example: Logic to handle user selection for multiple-choice question
        // You may need to adapt this based on your actual question structure
        // This example assumes that only one option can be selected
        // You might need to modify it if multiple options can be selected
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).isChecked()) {
                // Handle the user's selection (e.g., store it in SharedPreferences)
            }
        }
    }

    @Override
    protected boolean checkAnswer() {
        // Example: Logic to check the correctness of the user's answer for multiple-choice question
        // You need to replace this with your actual logic
        // This example assumes a correct answer is at position 0
        return checkBoxes.get(0).isChecked();
    }

    @Override
    protected void moveToNextQuestion() {
        // Example: Logic to move to the next question for multiple-choice question
        // You need to replace this with your actual logic
        // This example increments the current question index
        currentQuestionIndex++;

        // Reset checkboxes for the new question
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setChecked(false);
        }

        // Load UI components for the next question
        setUpQuestionType();
    }
}