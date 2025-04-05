package com.example.schoolboardapp;

import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if onboarding has already been shown
        SharedPreferences prefs = getSharedPreferences("onboarding", MODE_PRIVATE);
        boolean isFirstTime = prefs.getBoolean("firstTime", true);

        if (isFirstTime) {
            // Mark onboarding as shown
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", false);
            editor.apply();

            // Start onboarding activity
            startActivity(new Intent(MainActivity.this, OnboardingActivity.class));
            finish(); // Prevent back button from returning here
            return;
        }

        // Normal layout load
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
