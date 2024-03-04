package com.csit.quizgame;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<QuizQuestion> quizQuestions;
    private List<List<String>> selectedOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Load the quiz questions
        loadQuizQuestions();

        // Initialize the list to store selected options for each question
        selectedOptions = new ArrayList<>();

        // Set up the submit button click listener
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check the selected options for each question
                showConfirmationDialog();
            }
        });
    }


    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to submit your answers?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked "Yes," proceed with submitting answers
                        checkAnswers();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked "No," do nothing or handle accordingly
                        Toast.makeText(HomeActivity.this, "Submission canceled", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }


    private void checkAnswers() {
        int totalQuestions = quizQuestions.size();
        int correctAnswers = 0;

        for (int i = 0; i < totalQuestions; i++) {
            QuizQuestion question = quizQuestions.get(i);
            List<String> selected = selectedOptions.get(i);
            List<String> correct = Arrays.asList(question.getCorrectAnswer());

            // Check if selected options match the correct answer
            if (selected.equals(correct)) {
                correctAnswers++;
            }
        }

        // Display the results or take further actions as needed
        showResults(correctAnswers, totalQuestions);
    }

    private void showResults(int correctAnswers, int totalQuestions) {
        String resultMessage = "You got " + correctAnswers + " out of " + totalQuestions + " correct!";
        showToast(resultMessage);
        // You can perform additional actions based on the results, such as storing them in SharedPreferences
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void loadQuizQuestions() {
        // Implement the logic to load or initialize quiz questions
        // (You can use the provided questions or load them from an external source)
    }




    // Method to handle checkbox selection
    public void onCheckboxSelected(View view) {
        CheckBox checkbox = (CheckBox) view;
        int questionIndex = getQuestionIndex(view);

        if (questionIndex != -1) {
            // Update the list of selected options for the corresponding question
            if (checkbox.isChecked()) {
                selectedOptions.get(questionIndex).add(checkbox.getText().toString());
            } else {
                selectedOptions.get(questionIndex).remove(checkbox.getText().toString());
            }
        }
    }

    // Method to handle radio button selection
    public void onRadioButtonSelected(View view) {
        RadioButton radioButton = (RadioButton) view;
        int questionIndex = getQuestionIndex(view);

        if (questionIndex != -1) {
            // Update the list of selected options for the corresponding question
            selectedOptions.get(questionIndex).clear();
            selectedOptions.get(questionIndex).add(radioButton.getText().toString());
        }
    }

    // Helper method to get the question index based on the view's ID
    private int getQuestionIndex(View view) {
        // Implement your logic to determine the question index based on the view's ID
        // You might use view.getId() or other attributes to identify the question
        // For simplicity, return a static index here
        return 0;
    }
}