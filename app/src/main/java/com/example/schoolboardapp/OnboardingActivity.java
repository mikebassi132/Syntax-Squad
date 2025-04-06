package com.example.schoolboardapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 onboardingViewPager;
    private Button nextButton;
    private OnboardingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        onboardingViewPager = findViewById(R.id.onboardingViewPager);
        nextButton = findViewById(R.id.nextButton);
        Button skipButton = findViewById(R.id.skipButton);
        skipButton.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
            finish(); // Prevent going back to onboarding
        });


        setupOnboardingItems();

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == adapter.getItemCount() - 1) {
                    nextButton.setText("Get Started");
                } else {
                    nextButton.setText("Next");
                }
            }
        });

        nextButton.setOnClickListener(v -> {
            if (onboardingViewPager.getCurrentItem() < adapter.getItemCount() - 1) {
                onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
            } else {
                startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
                finish(); // So the user can't go back to onboarding
            }
        });
    }

    private void setupOnboardingItems() {
        List<OnboardingItem> items = new ArrayList<>();

        items.add(new OnboardingItem(
                R.drawable.onboarding_image1,
                "Welcome to UFV!",
                "Access UFV resources, connect with campus life.",
                1,   // backgroundType (green top)
                626, // width in dp
                476  // height in dp
        ));

        items.add(new OnboardingItem(
                R.drawable.onboarding_image2,
                "Explore Campus",
                "Navigate classrooms, cafes, and more.",
                0,   // backgroundType (white)
                300, // width in dp
                300  // height in dp
        ));

        items.add(new OnboardingItem(
                R.drawable.onboarding_image3,
                "Engage & Learn",
                "Collaborate with your peers and instructors.",
                0,   // backgroundType (white)
                280, // width in dp
                280  // height in dp
        ));

        adapter = new OnboardingAdapter(items);
        onboardingViewPager.setAdapter(adapter);
    }

}
