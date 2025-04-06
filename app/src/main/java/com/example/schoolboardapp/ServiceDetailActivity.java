package com.example.schoolboardapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.AppCompatActivity;

public class ServiceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        // Back button handler
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());


        TextView titleText = findViewById(R.id.serviceTitleText);
        TextView descriptionText = findViewById(R.id.serviceDescriptionText);
        TextView locationText = findViewById(R.id.serviceLocationText);
        ImageView bannerImage = findViewById(R.id.serviceImage);
        Button bookBtn = findViewById(R.id.bookBtn);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String location = intent.getStringExtra("location");
        String bookingLink = intent.getStringExtra("bookingLink");

        titleText.setText(title);
        descriptionText.setText(description);
        locationText.setText(location);
        bannerImage.setImageResource(R.drawable.shared_service_image);

        bookBtn.setOnClickListener(v -> {
            if (bookingLink != null && !bookingLink.isEmpty()) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bookingLink)));
            } else {
                Toast.makeText(this, "No booking link available", Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(ServiceDetailActivity.this, NewHomeActivity.class));
                return true;
            } else if (itemId == R.id.nav_courses) {
                startActivity(new Intent(ServiceDetailActivity.this, CoursesActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(ServiceDetailActivity.this, StudentProfileActivity.class));
                return true;
            }
            return false;
        });

    }
}
