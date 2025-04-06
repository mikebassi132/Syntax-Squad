package com.example.schoolboardapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView, emailTextView;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameTextView = findViewById(R.id.profileName);
        emailTextView = findViewById(R.id.profileEmail);
        backButton = findViewById(R.id.backButton);

        // 🔙 Go back
        backButton.setOnClickListener(v -> finish());

        // 🔐 Get logged-in user info
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            nameTextView.setText("Hello, " + (user.getDisplayName() != null ? user.getDisplayName() : "UFV Student"));
            emailTextView.setText(user.getEmail());
        } else {
            nameTextView.setText("No user found");
            emailTextView.setText("-");
        }
    }
}
