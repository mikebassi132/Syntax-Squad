package com.example.schoolboardapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GradPlanActivity extends AppCompatActivity {

    private TextView tvCreditsCompleted, tvCreditsRemaining, tvGraduationDate, tvProgramName, tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad_plan);

        tvCreditsCompleted = findViewById(R.id.tvCreditsCompleted);
        tvCreditsRemaining = findViewById(R.id.tvCreditsRemaining);
        tvGraduationDate = findViewById(R.id.tvGraduationDate);
        tvProgramName = findViewById(R.id.tvProgramName);
        tvStatus = findViewById(R.id.tvStatus);

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("students")
                .child("student1");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String creditsCompleted = String.valueOf(snapshot.child("creditsCompleted").getValue());
                    String creditsRemaining = String.valueOf(snapshot.child("creditsRemaining").getValue());
                    String graduationDate = String.valueOf(snapshot.child("graduationDate").getValue());
                    String programName = String.valueOf(snapshot.child("programName").getValue());
                    String status = String.valueOf(snapshot.child("status").getValue());

                    tvCreditsCompleted.setText(creditsCompleted);
                    tvCreditsRemaining.setText(creditsRemaining);
                    tvGraduationDate.setText(graduationDate);
                    tvProgramName.setText(programName);
                    tvStatus.setText(status);
                } else {
                    Log.e("GradPlanDebug", "No data found at student1 node!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("GradPlanDebug", "Database error: " + error.getMessage());
            }
        });
    }
}
