package com.example.schoolboardapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentProfileActivity extends AppCompatActivity {

    private TextView fullNameTextView, studentIdTextView, emailTextView, programTextView, yearTextView;
    private ImageView profileImageView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        // Firebase Auth instance
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        profileImageView = findViewById(R.id.profileImage);
        fullNameTextView = findViewById(R.id.fullNameTextView);
        studentIdTextView = findViewById(R.id.studentIdTextView);
        emailTextView = findViewById(R.id.emailTextView);
        programTextView = findViewById(R.id.programTextView);
        yearTextView = findViewById(R.id.yearTextView);

        // Get current user
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Set dynamic fields
            fullNameTextView.setText(user.getDisplayName() != null ? user.getDisplayName() : "Student");
            emailTextView.setText(user.getEmail());

            // Placeholder values (can be pulled from database later)
            studentIdTextView.setText("UFV ID: TBD");
            programTextView.setText("Program: TBD");
            yearTextView.setText("Year: TBD");
        } else {
            Toast.makeText(this, "No user is logged in", Toast.LENGTH_SHORT).show();
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile); // Optional: highlight current tab

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(StudentProfileActivity.this, NewHomeActivity.class));
                return true;
            } else if (itemId == R.id.nav_courses) {
                startActivity(new Intent(StudentProfileActivity.this, CoursesActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                return true; // already on profile
            }

            return false;
        });

    }
}
