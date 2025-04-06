package com.example.schoolboardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolboardapp.RecyclerView.AppointmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.appointmentsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up button listeners
        Button professorButton = findViewById(R.id.btnProfessor);
        professorButton.setOnClickListener(v -> bookWithProfessor());

        Button advisorButton = findViewById(R.id.btnAdvisor);
        advisorButton.setOnClickListener(v -> bookWithAdvisor());

        Button counselorButton = findViewById(R.id.btnCounselor);
        counselorButton.setOnClickListener(v -> bookWithCounselor());

        // Set up Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_home); // Highlight the current tab if needed

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(AppointmentsActivity.this, NewHomeActivity.class));
                return true;
            } else if (itemId == R.id.nav_courses) {
                startActivity(new Intent(AppointmentsActivity.this, CoursesActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(AppointmentsActivity.this, StudentProfileActivity.class));
                return true;
            }

            return false;
        });
    }

    public void bookWithProfessor() {
        Toast.makeText(this, "Booking with Professor", Toast.LENGTH_SHORT).show();
        List<String> appointmentTimes = new ArrayList<>();
        appointmentTimes.add("9:00 AM");
        appointmentTimes.add("10:00 AM");
        appointmentTimes.add("11:00 AM");

        adapter = new AppointmentAdapter(appointmentTimes);
        recyclerView.setAdapter(adapter);
    }

    public void bookWithAdvisor() {
        Toast.makeText(this, "Booking with Advisor", Toast.LENGTH_SHORT).show();
        List<String> appointmentTimes = new ArrayList<>();
        appointmentTimes.add("1:00 PM");
        appointmentTimes.add("2:00 PM");
        appointmentTimes.add("3:00 PM");

        adapter = new AppointmentAdapter(appointmentTimes);
        recyclerView.setAdapter(adapter);
    }

    public void bookWithCounselor() {
        Toast.makeText(this, "Booking with Counselor", Toast.LENGTH_SHORT).show();
        List<String> appointmentTimes = new ArrayList<>();
        appointmentTimes.add("4:00 PM");
        appointmentTimes.add("5:00 PM");
        appointmentTimes.add("6:00 PM");

        adapter = new AppointmentAdapter(appointmentTimes);
        recyclerView.setAdapter(adapter);
    }
}
