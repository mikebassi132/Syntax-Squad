package com.example.schoolboardapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupGridClickListeners();
    }

    private void setupGridClickListeners() {
        findViewById(R.id.itemAppointments).setOnClickListener(v ->
                showToast("Appointments clicked"));

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

        findViewById(R.id.itemGradPlan).setOnClickListener(v ->
                showToast("Grad Plan clicked"));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
