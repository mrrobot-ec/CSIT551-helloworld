package com.csit.quizgame;

import java.util.List;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioButtonQuizFragment extends QuizFragment {

    private RadioGroup radioGroup;

    @Override
    protected void setUpQuestionType() {
        // Implement specific logic for radio button questions
        // Example: Set up radio button options dynamically
        List<String> options = quizQuestions.get(getCurrentQuestionIndex()).getOptions();

        for (int i = 0; i < options.size(); i++) {
            RadioButton radioButton = new RadioButton(requireContext());
            radioButton.setText(options.get(i));
            radioButton.setId(i); // Set a unique ID for each radio button
            radioGroup.addView(radioButton);
        }
    }

    @Override
    protected void onUserAnswerSelected() {

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

    }

    @Override
    protected boolean checkAnswer() {
        // Example: Logic to check the correctness of the user's answer for radio button question
        // You need to replace this with your actual logic
        int correctOptionIndex = 0; // Replace with the correct option index
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        // Check if the selected radio button matches the correct option
        return selectedRadioButtonId == correctOptionIndex;
    }

    @Override
    protected void moveToNextQuestion() {
        // Example: Logic to move to the next question for radio button question
        // You need to replace this with your actual logic
        currentQuestionIndex++;

        // Clear the selection in the RadioGroup for the new question
        radioGroup.clearCheck();

        // Load UI components for the next question
        setUpQuestionType();
    }
}
