package com.example.schoolboardapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    // Declare buttons
    private Button coursesButton;
    private Button appointmentButton;
    private Button mapButton;
    private Button eventsButton;
    private Button chatButton;
    private Button myUfvButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize buttons by ID
        coursesButton = findViewById(R.id.coursesButton);
        appointmentButton = findViewById(R.id.appointmentButton);
        mapButton = findViewById(R.id.mapButton);
        eventsButton = findViewById(R.id.eventsButton);
        chatButton = findViewById(R.id.chatButton);
        myUfvButton = findViewById(R.id.myUfvButton);

        // Set click listeners
        coursesButton.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Courses clicked", Toast.LENGTH_SHORT).show());

        appointmentButton.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Appointments clicked", Toast.LENGTH_SHORT).show());

        mapButton.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Map clicked", Toast.LENGTH_SHORT).show());

        eventsButton.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Events clicked", Toast.LENGTH_SHORT).show());

        chatButton.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Chat clicked", Toast.LENGTH_SHORT).show());

        myUfvButton.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "MyUFV clicked", Toast.LENGTH_SHORT).show());
    }
}
