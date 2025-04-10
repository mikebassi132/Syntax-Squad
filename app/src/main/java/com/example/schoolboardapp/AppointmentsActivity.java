package com.example.schoolboardapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppointmentOptionAdapter adapter;
    private List<AppointmentOption> options;
    private List<AppointmentOption> availableTimes;
    private boolean showingAvailableTimes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        recyclerView = findViewById(R.id.appointmentsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initial appointment options
        options = new ArrayList<>();
        options.add(new AppointmentOption("Professor", "Book with a professor to talk about assignments or grades"));
        options.add(new AppointmentOption("Counselor", "Book with a counselor for personal or mental health support"));
        options.add(new AppointmentOption("Advisor", "Book with an advisor to discuss your graduation plan"));

        adapter = new AppointmentOptionAdapter(this, options, null);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            switch (position) {
                case 0:
                    showProfessorSelection();
                    break;
                case 1:
                    showCounselorOrAdvisor("counselors");
                    break;
                case 2:
                    showCounselorOrAdvisor("advisors");
                    break;
            }
        });

        // Bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, NewHomeActivity.class));
                return true;
            } else if (itemId == R.id.nav_courses) {
                startActivity(new Intent(this, CoursesActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(this, StudentProfileActivity.class));
                return true;
            }
            return false;
        });
    }

    private void showProfessorSelection() {
        fetchAppointmentOptions("professors");
    }

    private void showCounselorOrAdvisor(String type) {
        fetchAppointmentOptions(type);
    }

    private void fetchAppointmentOptions(String type) {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference appointmentsRef = FirebaseDatabase.getInstance().getReference()
                .child("students")
                .child(uid)
                .child("student1") // ✅ Fix is here
                .child("appointments")
                .child(type);

        appointmentsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                availableTimes = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name = snapshot.child("name").getValue(String.class);
                        String availableTimesStr = snapshot.child("availableTimes").getValue(String.class);

                        if (availableTimesStr != null && name != null) {
                            String[] times = availableTimesStr.split(",\\s*");
                            for (String time : times) {
                                availableTimes.add(new AppointmentOption(name, time));
                            }
                        }
                    }

                    showingAvailableTimes = true;
                    adapter = new AppointmentOptionAdapter(AppointmentsActivity.this, availableTimes, null);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(position -> {
                        AppointmentOption selected = availableTimes.get(position);
                        Toast.makeText(AppointmentsActivity.this, "Selected: " + selected.getProfessorName() + " at " + selected.getAvailableTime(), Toast.LENGTH_SHORT).show();
                    });

                } else {
                    Toast.makeText(AppointmentsActivity.this, "No available appointments found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AppointmentsActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
