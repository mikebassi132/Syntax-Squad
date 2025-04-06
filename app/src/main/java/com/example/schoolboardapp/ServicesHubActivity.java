package com.example.schoolboardapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;


import java.util.ArrayList;
import java.util.List;

public class ServicesHubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_hub);

        RecyclerView recyclerView = findViewById(R.id.servicesRecyclerView);
        ImageView backButton = findViewById(R.id.backButton);

        List<ServiceItem> items = new ArrayList<>();
        items.add(new ServiceItem(
                R.drawable.img_learning,
                "Learning & Exam Support",
                "Get guidance on program requirements and course planning.\n" +
                        "Explore programs and opportunities that meet your abilities, interests, and career goals and discuss UFV resources and supports available to you.\n" +
                        "Check to see if you are on track for graduation.",
                "G Building, Room 211",
                "https://www.ufv.ca/learning-support"
        ));

        items.add(new ServiceItem(
                R.drawable.img_career,
                "Career Services",
                "RBuild your career readiness and connect with employers. Gain relevant work experience with Co-operative Education.\n" +
                        "Career coaching for assistance with your resume, cover letter, job search, or interview skills.",
                "Career Center, Room 105",
                "https://www.ufv.ca/jobs"
        ));

        items.add(new ServiceItem(
                R.drawable.img_health,
                "Health & Wellness",
                "Prioritize your well-being with UFV's Health & Wellness services. Access physical and mental health resources, counselling support, and wellness programs.\n" +
                        "Stay healthy, stay focused — we’re here to support your journey.\n" +
                        "\n",
                "U House, Abbotsford",
                "https://www.ufv.ca/studentservices/health-wellness/"
        ));

        items.add(new ServiceItem(
                R.drawable.img_finance,
                "Financial Support",
                "Get help funding your education with UFV's financial aid and awards. Explore bursaries, scholarships, emergency assistance, and budgeting tools.\n" +
                        "Invest in your future — we’re here to support every step of the way.",
                "S Building, Finance Desk",
                "https://www.ufv.ca/fineaid"
        ));

        items.add(new ServiceItem(
                R.drawable.img_safety,
                "Campus Safety",
                "Your safety is our priority. UFV Campus Safety provides 24/7 support, SafeWalk services, emergency preparedness, and incident reporting tools.\n" +
                        "Feel secure and supported — on campus and beyond.",
                "Security Office, Building D",
                "https://www.ufv.ca/security"
        ));



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ServiceItemAdapter(items));

        backButton.setOnClickListener(v -> finish());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(ServicesHubActivity.this, NewHomeActivity.class));
                return true;
            } else if (itemId == R.id.nav_courses) {
                startActivity(new Intent(ServicesHubActivity.this, CoursesActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(ServicesHubActivity.this, StudentProfileActivity.class));
                return true;
            }
            return false;
        });

    }
}
