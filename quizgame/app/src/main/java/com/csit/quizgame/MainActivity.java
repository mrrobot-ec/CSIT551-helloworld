package com.csit.quizgame;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.buttonLogin);
        TextView registerButton = findViewById(R.id.textRegister);
        EditText emailEditText = findViewById(R.id.editEmailAddress);
        EditText passwordEditText = findViewById(R.id.editPassword);

        loginButton.setEnabled(true);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        loginButton.setEnabled(true);
                    }
                }
        );

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                launcher.launch(intent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateLoginForm(emailEditText, passwordEditText)) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password); // Add more password criteria if needed
    }

    public static boolean validateLoginForm(EditText emailEditText,
                                                   EditText passwordEditText) {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean isValid = true;

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