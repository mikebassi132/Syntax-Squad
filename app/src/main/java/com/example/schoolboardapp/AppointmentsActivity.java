package com.example.schoolboardapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolboardapp.RecyclerView.AppointmentAdapter;

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
    }

    // Method for booking with professor
    public void bookWithProfessor() {
        Toast.makeText(this, "Booking with Professor", Toast.LENGTH_SHORT).show();
        // Set available appointment times for Professor
        List<String> appointmentTimes = new ArrayList<>();
        appointmentTimes.add("9:00 AM");
        appointmentTimes.add("10:00 AM");
        appointmentTimes.add("11:00 AM");

        // Set the adapter with the list of appointment times
        adapter = new AppointmentAdapter(appointmentTimes);
        recyclerView.setAdapter(adapter);
    }

    // Method for booking with advisor
    public void bookWithAdvisor() {
        Toast.makeText(this, "Booking with Advisor", Toast.LENGTH_SHORT).show();
        // Set available appointment times for Advisor
        List<String> appointmentTimes = new ArrayList<>();
        appointmentTimes.add("1:00 PM");
        appointmentTimes.add("2:00 PM");
        appointmentTimes.add("3:00 PM");

        // Set the adapter with the list of appointment times
        adapter = new AppointmentAdapter(appointmentTimes);
        recyclerView.setAdapter(adapter);
    }

    // Method for booking with counselor
    public void bookWithCounselor() {
        Toast.makeText(this, "Booking with Counselor", Toast.LENGTH_SHORT).show();
        // Set available appointment times for Counselor
        List<String> appointmentTimes = new ArrayList<>();
        appointmentTimes.add("4:00 PM");
        appointmentTimes.add("5:00 PM");
        appointmentTimes.add("6:00 PM");

        // Set the adapter with the list of appointment times
        adapter = new AppointmentAdapter(appointmentTimes);
        recyclerView.setAdapter(adapter);
    }
}
