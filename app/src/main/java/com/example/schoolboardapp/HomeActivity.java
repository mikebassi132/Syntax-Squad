package com.example.schoolboardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupGridClickListeners();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_courses) {
                Intent intent = new Intent(HomeActivity.this, CoursesActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void setupGridClickListeners() {
        // "Appointments"
        findViewById(R.id.itemAppointments).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AppointmentsActivity.class);
            startActivity(intent);
        });

        // Others showing toast
        findViewById(R.id.itemMap).setOnClickListener(v ->
                showToast("Map clicked"));

        findViewById(R.id.itemEvents).setOnClickListener(v ->
                showToast("Events clicked"));

        findViewById(R.id.itemChat).setOnClickListener(v ->
                showToast("Chat clicked"));

        findViewById(R.id.itemRegistration).setOnClickListener(v ->
                showToast("Registration clicked"));

        findViewById(R.id.itemProfile).setOnClickListener(v ->
                showToast("Student Profile clicked"));

        findViewById(R.id.itemInfo).setOnClickListener(v ->
                showToast("Information clicked"));

        findViewById(R.id.itemBilling).setOnClickListener(v ->
                showToast("Billing clicked"));

        findViewById(R.id.itemAid).setOnClickListener(v ->
                showToast("Financial Aid clicked"));

        //  Grad Plan now opens GradPlanActivity instead of showing toast
        LinearLayout gradPlanCard = findViewById(R.id.itemGradPlan);
        gradPlanCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, GradPlanActivity.class);
            startActivity(intent);
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
