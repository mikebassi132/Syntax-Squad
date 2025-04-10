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

import com.google.firebase.auth.FirebaseAuth;
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

        // Get current user UID
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("students")
                .child(uid)
                .child("student1");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(GradPlanActivity.this, "No data found for student1.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Safely retrieve credits
                    Integer creditsCompletedObj = snapshot.child("creditsCompleted").getValue(Integer.class);
                    Integer creditsRemainingObj = snapshot.child("creditsRemaining").getValue(Integer.class);

                    int creditsCompleted = creditsCompletedObj != null ? creditsCompletedObj : 0;
                    int creditsRemaining = creditsRemainingObj != null ? creditsRemainingObj : 0;

                    String graduationDate = String.valueOf(snapshot.child("graduationDate").getValue());
                    String programName = String.valueOf(snapshot.child("programName").getValue());
                    String status = String.valueOf(snapshot.child("status").getValue());

                    tvCreditsCompleted.setText("Credits Completed: " + creditsCompleted);
                    tvCreditsRemaining.setText("Credits Remaining: " + creditsRemaining);
                    tvGraduationDate.setText("Graduation Date: " + graduationDate);
                    tvProgramName.setText("Program Name: " + programName);
                    tvStatus.setText("Status: " + status);

                    int total = creditsCompleted + creditsRemaining;
                    int percentage = total > 0 ? (int) ((creditsCompleted * 100.0f) / total) : 0;
                    progressCredits.setProgress(percentage);
                    addTextView(layoutCurrentSemester, percentage + "% Completed");

                    // ✅ Current Semester check
                    DataSnapshot winterCourses = snapshot.child("currentSemester").child("winter2025");
                    if (winterCourses.exists() && winterCourses.hasChildren()) {
                        for (DataSnapshot courseSnap : winterCourses.getChildren()) {
                            GradCourse course = courseSnap.getValue(GradCourse.class);
                            if (course != null) {
                                String text = "• " + course.name + " (" + course.credits + " credits)";
                                transcriptCurrent.add(text);
                                addTextView(layoutCurrentSemester, text);
                            }
                        }
                    } else {
                        addTextView(layoutCurrentSemester, "No current semester data.");
                    }

                    // ✅ Course History check
                    DataSnapshot courseHistory = snapshot.child("courseHistory");
                    if (courseHistory.exists() && courseHistory.hasChildren()) {
                        for (DataSnapshot semesterSnap : courseHistory.getChildren()) {
                            String semester = semesterSnap.getKey();
                            if (semester == null) continue;

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
                                semesterLayout.setVisibility(
                                        semesterLayout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE
                                );
                            });

                            layoutCourseHistory.addView(semesterHeader);
                            layoutCourseHistory.addView(semesterLayout);
                        }
                    } else {
                        addTextView(layoutCourseHistory, "No course history available.");
                    }

                } catch (Exception e) {
                    Log.e("GradPlanError", "Error parsing grad plan data", e);
                    Toast.makeText(GradPlanActivity.this, "Error loading grad plan.", Toast.LENGTH_SHORT).show();
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
