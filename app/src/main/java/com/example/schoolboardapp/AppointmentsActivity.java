package com.example.schoolboardapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        RecyclerView recyclerView = findViewById(R.id.appointmentsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<AppointmentOption> options = new ArrayList<>();
        options.add(new AppointmentOption(R.drawable.ic_launcher_foreground, "Book with Professor", "Talk about assignments or grades."));
        options.add(new AppointmentOption(R.drawable.ic_launcher_foreground, "Book with Advisor", "Plan your courses and graduation path."));
        options.add(new AppointmentOption(R.drawable.ic_launcher_foreground, "Book with Counselor", "Discuss personal or mental health issues."));

        AppointmentOptionAdapter adapter = new AppointmentOptionAdapter(this, options);
        recyclerView.setAdapter(adapter);

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
}
