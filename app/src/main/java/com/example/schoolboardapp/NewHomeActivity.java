package com.example.schoolboardapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewHomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView greetingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);

        greetingText = findViewById(R.id.greetingText);
        recyclerView = findViewById(R.id.homeCardRecyclerView);

        // You can fetch actual username from Firebase/Auth later
        greetingText.setText("Hey, Test");

        List<HomeCardItem> items = new ArrayList<>();
        items.add(new HomeCardItem(R.drawable.img_services, "Services Hub", "Access all UFV services", 0xFF7CB232));
        items.add(new HomeCardItem(R.drawable.img_courses, "Your Courses", "Track enrolled classes", 0xFF7CB232));
        items.add(new HomeCardItem(R.drawable.img_events, "Upcoming Events", "Stay updated with campus life", 0xFF7CB232));
        items.add(new HomeCardItem(R.drawable.img_appointment, "Book an Appointment", "Meet advisors, profs, counselors", 0xFF7CB232));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HomeCardAdapter(items, this));
    }
}
