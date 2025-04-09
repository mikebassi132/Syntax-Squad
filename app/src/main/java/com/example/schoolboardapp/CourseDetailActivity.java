package com.example.schoolboardapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        // 🧭 Back button logic
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // 📝 Get views
        TextView title = findViewById(R.id.courseDetailTitle);
        TextView content = findViewById(R.id.courseDetailContent);
        ImageView image = findViewById(R.id.courseDetailImage);
        Button sendLeaveBtn = findViewById(R.id.sendLeaveButton);

        // 📨 Get course data from intent
        String courseCode = getIntent().getStringExtra("code");
        String description = getIntent().getStringExtra("description");
        int imageRes = getIntent().getIntExtra("image", R.drawable.img_courses);

        // 🧾 Set content to views
        title.setText(courseCode);
        content.setText(description);
        image.setImageResource(imageRes);

        // 📧 Email Intent Setup
        sendLeaveBtn.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:professor@ufv.ca")); // ✅ Correct format
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Leave Request for " + courseCode);
            emailIntent.putExtra(Intent.EXTRA_TEXT,
                    "Dear Professor,\n\nI would like to request a leave for the course " +
                            courseCode + ".\n\nReason: \n\nThank you.");

            // ✅ Ensure an email app is available
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(emailIntent, "Send Leave Request via Email"));
            } else {
                Toast.makeText(this, "No email app found!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
