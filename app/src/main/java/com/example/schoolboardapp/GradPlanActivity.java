package com.example.schoolboardapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class GradPlanActivity extends AppCompatActivity {

    private TextView tvCreditsCompleted, tvCreditsRemaining, tvGraduationDate, tvProgramName, tvStatus;
    private LinearLayout layoutCurrentSemester, layoutCourseHistory;
    private ProgressBar progressCredits;
    private Button btnDownloadTranscript;

    private final List<String> transcriptCurrent = new ArrayList<>();
    private final List<String> transcriptHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad_plan);

        // UI References
        tvCreditsCompleted = findViewById(R.id.tvCreditsCompleted);
        tvCreditsRemaining = findViewById(R.id.tvCreditsRemaining);
        tvGraduationDate = findViewById(R.id.tvGraduationDate);
        tvProgramName = findViewById(R.id.tvProgramName);
        tvStatus = findViewById(R.id.tvStatus);
        layoutCurrentSemester = findViewById(R.id.layoutCurrentSemester);
        layoutCourseHistory = findViewById(R.id.layoutCourseHistory);
        progressCredits = findViewById(R.id.progressCredits);
        btnDownloadTranscript = findViewById(R.id.btnDownloadTranscript);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("students").child("student1");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int creditsCompleted = snapshot.child("creditsCompleted").getValue(Integer.class);
                int creditsRemaining = snapshot.child("creditsRemaining").getValue(Integer.class);
                String graduationDate = String.valueOf(snapshot.child("graduationDate").getValue());
                String programName = String.valueOf(snapshot.child("programName").getValue());
                String status = String.valueOf(snapshot.child("status").getValue());

                // Display info
                tvCreditsCompleted.setText("Credits Completed: " + creditsCompleted);
                tvCreditsRemaining.setText("Credits Remaining: " + creditsRemaining);
                tvGraduationDate.setText("Graduation Date: " + graduationDate);
                tvProgramName.setText("Program Name: " + programName);
                tvStatus.setText("Status: " + status);

                // Progress bar
                int total = creditsCompleted + creditsRemaining;
                int percentage = (int) ((creditsCompleted * 100.0f) / total);
                progressCredits.setProgress(percentage);
                addTextView(layoutCurrentSemester, percentage + "% Completed");

                // 🟢 Current Semester (Winter2025)
                DataSnapshot winterCourses = snapshot.child("currentSemester").child("winter2025");
                for (DataSnapshot courseSnap : winterCourses.getChildren()) {
                    GradCourse course = courseSnap.getValue(GradCourse.class);
                    if (course != null) {
                        String text = "• " + course.name + " (" + course.credits + " credits)";
                        transcriptCurrent.add(text);
                        addTextView(layoutCurrentSemester, text);
                    }
                }

                // 🟠 Course History (Fall2024, Summer2024, etc.)
                DataSnapshot courseHistory = snapshot.child("courseHistory");
                for (DataSnapshot semesterSnap : courseHistory.getChildren()) {
                    String semester = semesterSnap.getKey();
                    LinearLayout semesterLayout = new LinearLayout(GradPlanActivity.this);
                    semesterLayout.setOrientation(LinearLayout.VERTICAL);
                    semesterLayout.setVisibility(View.GONE);

                    for (DataSnapshot courseSnap : semesterSnap.getChildren()) {
                        GradCourse course = courseSnap.getValue(GradCourse.class);
                        if (course != null) {
                            String text = "• " + course.name + " - Grade: " + course.grade + ", Credits: " + course.credits;
                            transcriptHistory.add(text);
                            addTextView(semesterLayout, text);
                        }
                    }

                    TextView semesterHeader = new TextView(GradPlanActivity.this);
                    semesterHeader.setText(semester.substring(0, 1).toUpperCase() + semester.substring(1));
                    semesterHeader.setTextSize(16);
                    semesterHeader.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                    semesterHeader.setPadding(0, 16, 0, 4);
                    semesterHeader.setOnClickListener(v -> {
                        semesterLayout.setVisibility(semesterLayout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                    });

                    layoutCourseHistory.addView(semesterHeader);
                    layoutCourseHistory.addView(semesterLayout);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("GradPlanDebug", "Database error: " + error.getMessage());
            }
        });

        btnDownloadTranscript.setOnClickListener(v -> {
            PDFUtils.generateTranscriptPDF(
                    GradPlanActivity.this,
                    transcriptCurrent,
                    transcriptHistory,
                    "Student1"
            );
        });
    }

    private void addTextView(LinearLayout layout, String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        layout.addView(tv);
    }
}
