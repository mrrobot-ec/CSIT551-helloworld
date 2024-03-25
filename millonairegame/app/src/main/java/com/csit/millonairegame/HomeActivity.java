package com.csit.millonairegame;

import java.util.List;
import java.util.Arrays;
import android.view.View;
import android.os.Bundle;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private List<Question> questions = new ArrayList<>();
    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital city of Australia?", Arrays.asList("Sydney", "Melbourne", "Canberra", "Perth"), "Canberra", 1000));
        questions.add(new Question("Who wrote the famous novel 'To Kill a Mockingbird'?", Arrays.asList("Harper Lee", "J.K. Rowling", "Stephen King", "Charles Dickens"), "Harper Lee", 2000));
        questions.add(new Question("What is the chemical symbol for gold?", Arrays.asList("Au", "Ag", "Fe", "Cu"), "Au", 5000));
        questions.add(new Question("What is the main ingredient in guacamole?", Arrays.asList("Tomato", "Avocado", "Onion", "Lime"), "Avocado", 10000));
        questions.add(new Question("What is the largest organ in the human body?", Arrays.asList("Heart", "Brain", "Liver", "Skin"), "Skin", 20000));
        questions.add(new Question("Which country is known as the Land of the Rising Sun?", Arrays.asList("China", "India", "Japan", "South Korea"), "Japan", 50000));
        questions.add(new Question("Who painted the Mona Lisa?", Arrays.asList("Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Michelangelo"), "Leonardo da Vinci", 75000));
        questions.add(new Question("Which of the following is not a mammal?", Arrays.asList("Elephant", "Whale", "Turtle", "Gorilla"), "Turtle", 100000));
        questions.add(new Question("What is the tallest mountain in the world?", Arrays.asList("Mount Kilimanjaro", "Mount Everest", "Mount Fuji", "Mount McKinley"), "Mount Everest", 250000));
        questions.add(new Question("Which of the following is not a prime number?", Arrays.asList("11", "13", "15", "17"), "15", 500000));

        String jsonQuestions = Question.questionsToJson(questions);
        PreferencesUtil.saveQuestionsJson(getApplicationContext(), jsonQuestions);

        loadQuestions();
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question currentQuestion = questions.get(index);
                RadioGroup rgSingleChoice = findViewById(R.id.rOptions);
                int selectedId = rgSingleChoice.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selectedOption = findViewById(selectedId);
                    boolean correct = selectedOption.getText().toString().equals(currentQuestion.getCorrectAnswer());
                    if(correct) {
                        Toast.makeText(HomeActivity.this, "Answer is correct!", Toast.LENGTH_SHORT).show();
                        index++;
                        if (index < questions.size()) {
                            showQuestions(index);
                        } else {
                            Intent intent = new Intent(HomeActivity.this, ScoreActivity.class);
                            intent.putExtra("score", currentQuestion.getCashReward());
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(HomeActivity.this, "Answer is not correct!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomeActivity.this, ScoreActivity.class);
                        intent.putExtra("score", currentQuestion.getCashReward());
                        startActivity(intent);
                    }
                }else {
                    Toast.makeText(HomeActivity.this, "Choose an option", Toast.LENGTH_SHORT).show();
                }
                    }
        });
    }
    private void showOptions(Question question, RadioGroup rOptions) {
        for (String option : question.getOptions()) {
            RadioButton rb = new RadioButton(this);
            rb.setText(option);
            rOptions.addView(rb);
        }
    }
    private void loadQuestions() {
        String jsonQuestions = PreferencesUtil.getQuestionsJson(this);
        questions = Question.jsonToQuestions(jsonQuestions);
        showQuestions(index);
    }

    private void showQuestions(int questionIndex) {
        Question question = questions.get(questionIndex);
        TextView tvQuestion = findViewById(R.id.lblQuestionText);
        RadioGroup rgSingleChoice = findViewById(R.id.rOptions);
        tvQuestion.setText(question.getQuestionText());
        rgSingleChoice.removeAllViews();
        showOptions(question, rgSingleChoice);
    }
}