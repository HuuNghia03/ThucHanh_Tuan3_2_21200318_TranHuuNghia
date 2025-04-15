package com.example.tuan3_2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tuan3_2.R;

public class RegisterActivity extends AppCompatActivity {
    ImageView eyeIcon;
    Button registerButton, loginButton;
    EditText firstnameEditText, lastnameEditText, emailEditText, passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        eyeIcon = findViewById(R.id.eye_icon);
        passwordEditText = findViewById(R.id.password);
        firstnameEditText = findViewById(R.id.firstname);
        lastnameEditText = findViewById(R.id.lastname);
        emailEditText = findViewById(R.id.username);
        registerButton = findViewById(R.id.registerButton);
        loginButton=findViewById(R.id.loginButton);
        eyeIcon.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = false;

            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyeIcon.setImageResource(R.drawable.eye_ic_closed);
                } else {
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyeIcon.setImageResource(R.drawable.eye_ic_open);
                }

                // Set lại font, size, và vị trí con trỏ
                passwordEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                passwordEditText.setSelection(passwordEditText.length());
                isPasswordVisible = !isPasswordVisible;
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = firstnameEditText.getText().toString().trim();
                String lastname = lastnameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {


                    startActivity(new Intent(RegisterActivity.this, com.example.tuan3_2.LoginActivity.class)); // Quay về màn hình trước
                    Toast.makeText(RegisterActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, com.example.tuan3_2.LoginActivity.class)); // Quay về màn hình trước

            }
        });

    }
}
