package com.csit.gpa_campoverdea4_calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout gradeOneTxt, gradeTwoTxt, gradeThreeTxt, gradeFourTxt, gradeFiveTxt;
    private TextView lblTotal;
    private ScrollView scroll;

    private String gradeOne, gradeTwo, gradeThree, gradeFour, gradeFive, total;
    private boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateBtn = findViewById(R.id.btn_calculate);
        this.gradeOneTxt = findViewById(R.id.input_grade_one);
        this.gradeTwoTxt = findViewById(R.id.input_grade_two);
        this.gradeThreeTxt = findViewById(R.id.input_grade_three);
        this.gradeFourTxt = findViewById(R.id.input_grade_four);
        this.gradeFiveTxt = findViewById(R.id.input_grade_five);
        this.lblTotal = findViewById(R.id.lbl_total);
        TextInputEditText gradeOneEdit = findViewById(R.id.grade_one_edit);
        TextInputEditText gradeTwoEdit = findViewById(R.id.grade_two_edit);
        TextInputEditText gradeThreeEdit = findViewById(R.id.grade_three_edit);
        TextInputEditText gradeFourEdit = findViewById(R.id.grade_four_edit);
        TextInputEditText gradeFiveEdit = findViewById(R.id.grade_five_edit);

        gradeOneEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBtn.setText(R.string.calculate);
            }
        });
        gradeTwoEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                calculateBtn.setText(R.string.calculate);
                return false;
            }
        });
        gradeThreeEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                calculateBtn.setText(R.string.calculate);
                return false;
            }
        });
        gradeFourEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                calculateBtn.setText(R.string.calculate);
                return false;
            }
        });
        gradeFiveEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                calculateBtn.setText(R.string.calculate);
                return false;
            }
        });

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculateBtn.getText().toString().equalsIgnoreCase((String) getResources().getText(R.string.clear))) {
                    gradeOneTxt.getEditText().setText("");
                    gradeTwoTxt.getEditText().setText("");
                    gradeThreeTxt.getEditText().setText("");
                    gradeFourTxt.getEditText().setText("");
                    gradeFiveTxt.getEditText().setText("");
                    gradeOneTxt.setError(null);
                    gradeTwoTxt.setError(null);
                    gradeThreeTxt.setError(null);
                    gradeFourTxt.setError(null);
                    gradeFiveTxt.setError(null);
                    scroll.setBackgroundColor(getResources().getColor(android.R.color.white, null));
                }
                Log.v("calculateBtn", "entra");
                isAllFieldsChecked = CheckAllFields();
                Log.v("calculateBtn", "isAllFieldsChecked" + isAllFieldsChecked);
                if (isAllFieldsChecked && calculateBtn.getText().toString().equalsIgnoreCase((String) getResources().getText(R.string.calculate))) {
                    Log.v("calculateBtn", "entra if");
                    total = String.valueOf((Float.parseFloat(gradeOne) +
                            Float.parseFloat(gradeTwo) +
                            Float.parseFloat(gradeTwo) +
                            Float.parseFloat(gradeThree) +
                            Float.parseFloat(gradeFour) +
                            Float.parseFloat(gradeFive)) / 5);
                    lblTotal.setText(total);
                    calculateBtn.setText(R.string.clear);
                    scroll = findViewById(R.id.scroll);
                    if (Float.parseFloat(total) < 60) {
                        scroll.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light, null));
                    } else if (Float.parseFloat(total) >= 61 && Float.parseFloat(total) <= 79) {
                        scroll.setBackgroundColor(getResources().getColor(R.color.yellow, null));
                    } else if (Float.parseFloat(total) >= 80 && Float.parseFloat(total) <= 100) {
                        scroll.setBackgroundColor(getResources().getColor(R.color.green, null));
                    }
                }
            }
        });
    }

    private boolean CheckAllFields() {
        this.gradeOne = String.valueOf(gradeOneTxt.getEditText().getText()).trim();
        this.gradeTwo = String.valueOf(gradeTwoTxt.getEditText().getText()).trim();
        this.gradeThree = String.valueOf(gradeThreeTxt.getEditText().getText()).trim();
        this.gradeFour = String.valueOf(gradeFourTxt.getEditText().getText()).trim();
        this.gradeFive = String.valueOf(gradeFiveTxt.getEditText().getText()).trim();
        if (this.gradeOne.length() == 0) {
            this.gradeOneTxt.setError("This field is required");
            return false;
        }
        if (this.gradeTwo.length() == 0) {
            this.gradeTwoTxt.setError("This field is required");
            return false;
        }
        if (this.gradeThree.length() == 0) {
            this.gradeThreeTxt.setError("This field is required");
            return false;
        }
        if (this.gradeFour.length() == 0) {
            this.gradeFourTxt.setError("This field is required");
            return false;
        }
        if (this.gradeFive.length() == 0) {
            this.gradeFiveTxt.setError("This field is required");
            return false;
        }
        return true;
    }
}