package com.csit.quizgame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public abstract class QuizFragment extends Fragment {

    protected List<CheckBox> checkBoxes = new ArrayList<>();
    protected List<QuizQuestion> quizQuestions;
    protected int currentQuestionIndex;

    // Use this RadioGroup for RadioButtonQuizFragment
    protected RadioGroup radioGroup;

    // Override this method to set up question-specific UI components
    protected abstract void setUpQuestionType();

    // Override this method to handle user selection for the current question
    protected abstract void onUserAnswerSelected();

    // Override this method to check the correctness of the user's answer
    protected abstract boolean checkAnswer();

    // Override this method to move to the next question
    protected abstract void moveToNextQuestion();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_fragment, container, false);

        // Set up question-specific UI components
        setUpQuestionType();

        return view;
    }

    // Method to handle the user's answer for the current question
    protected void handleUserAnswer() {
        onUserAnswerSelected();

        // Check the correctness of the user's answer
        boolean isCorrect = checkAnswer();

        // Move to the next question
        if (isCorrect) {
            moveToNextQuestion();
        }
    }

    // Method to retrieve the current question index
    protected int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    // Method to load quiz questions (to be called before displaying the fragment)
    public void loadQuizQuestions(List<QuizQuestion> questions) {
        quizQuestions = new ArrayList<>(questions);
        currentQuestionIndex = 0;
    }
}
