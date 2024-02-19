package com.csit.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button button = findViewById(R.id.buttonRegister);
        EditText firstNameEditText = findViewById(R.id.firstNameEditText);
        EditText familyNameEditText = findViewById(R.id.familyNameEditText);
        EditText dobEditText = findViewById(R.id.dobEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(validateRegistrationForm(firstNameEditText, familyNameEditText,
                        dobEditText, emailEditText, passwordEditText)){
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.putExtra("enableLogin",true);
                    setResult(RESULT_OK,intent);
                    finish();
                };
            }
        });
    }

    public static boolean isFirstNameValid(String firstName) {
        return !TextUtils.isEmpty(firstName) && firstName.length() >= 3 && firstName.length() <= 30;
    }

    public static boolean isFamilyNameValid(String familyName) {
        return !TextUtils.isEmpty(familyName);
    }

    public static boolean isDateOfBirthValid(String dateOfBirth) {
        return !TextUtils.isEmpty(dateOfBirth);
    }

    public static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password); // Add more password criteria if needed
    }

    public static boolean validateRegistrationForm(EditText firstNameEditText,
                                                   EditText familyNameEditText,
                                                   EditText dobEditText,
                                                   EditText emailEditText,
                                                   EditText passwordEditText) {

        String firstName = firstNameEditText.getText().toString();
        String familyName = familyNameEditText.getText().toString();
        String dob = dobEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean isValid = true;

        if (!isFirstNameValid(firstName)) {
            firstNameEditText.setError("Enter a valid first name (3-30 characters)");
            isValid = false;
        }

        if (!isFamilyNameValid(familyName)) {
            familyNameEditText.setError("Family name cannot be empty");
            isValid = false;
        }

        if (!isDateOfBirthValid(dob)) {
            dobEditText.setError("Date of birth cannot be empty");
            isValid = false;
        }

        if (!isEmailValid(email)) {
            emailEditText.setError("Enter a valid email address");
            isValid = false;
        }

        if (!isPasswordValid(password)) {
            passwordEditText.setError("Password cannot be empty");
            isValid = false;
        }

        return isValid;
    }
}