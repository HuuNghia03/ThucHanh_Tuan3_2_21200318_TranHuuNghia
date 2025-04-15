package com.example.tuan3_2;
import android.content.Intent;
import android.graphics.Color;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tuan3_2.R;

public class LoginActivity  extends AppCompatActivity {
    TextView forgotpwTextView;
    ImageView eyeIcon;
    EditText passwordEditText,emailEditText;
    Button loginButton, registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        forgotpwTextView=findViewById(R.id.tvForgotPassword);
        eyeIcon = findViewById(R.id.eye_icon);
        emailEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registerButton=findViewById(R.id.registerButton);
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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ email và mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, com.example.tuan3_2.RegisterActivity.class));
            }
        });

        forgotpwTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForgotPasswordDialog();
            }
        });
    }
    private void showForgotPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Layout tổng
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(80, 60, 80, 60);
        // Tiêu đề
        TextView customTitle = new TextView(this);
        customTitle.setText("Forgot your password?");
        customTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
        customTitle.setTextColor(Color.BLACK); // Màu chữ đen
        customTitle.setPadding(10, 10, 10, 30);
        customTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        layout.addView(customTitle);

        // Nội dung
        TextView message = new TextView(this);
        message.setText("Enter your email address, we will send your password.");
        message.setTextSize(18);
        message.setTextColor(Color.BLACK); // Màu chữ đen
        message.setPadding(10, 50, 10, 30);
        layout.addView(message);

        // Ô nhập email
        EditText emailInput = new EditText(this);
        emailInput.setHint("Email address");
        emailInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        emailInput.setPadding(30, 40, 30, 30);
        emailInput.setTextSize(16);
        emailInput.setTextColor(Color.BLACK); // Màu chữ đen
        layout.addView(emailInput);

        builder.setView(layout);

        // Nút Gửi
        builder.setPositiveButton("Send", (dialog, which) -> {
            String email = emailInput.getText().toString().trim();
            if (!email.isEmpty()) {
                Toast.makeText(this, "Password sent to " + email, Toast.LENGTH_SHORT).show();

            } else {
                emailInput.setError("Please enter email.");
            }
        });
        // Nút Hủy
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
        // Phóng to cửa sổ
        dialog.getWindow().setLayout(1000, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
