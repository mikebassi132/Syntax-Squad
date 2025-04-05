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
    private Button buttonNext, buttonSkip;
    private OnboardingItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        onboardingViewPager = findViewById(R.id.onboardingViewPager);
        buttonNext = findViewById(R.id.buttonNext);
        buttonSkip = findViewById(R.id.buttonSkip);

        setupOnboardingItems();

        buttonNext.setOnClickListener(v -> {
            if (onboardingViewPager.getCurrentItem() + 1 < adapter.getItemCount()) {
                onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
            } else {
                navigateToLogin();
            }
        });

        buttonSkip.setOnClickListener(v -> navigateToLogin());
    }

    private void setupOnboardingItems() {
        List<OnboardingItem> items = new ArrayList<>();

        items.add(new OnboardingItem(R.drawable.onboarding_image1,
                "Welcome to goUFV!",
                "Access UFV resources, connect with campus life, and stay informed—anytime, anywhere.",
                1,
                934,
                1176)); // GREEN

        items.add(new OnboardingItem(R.drawable.onboarding_image2,
                "Explore UFV, Inside and Out",
                "Navigate classrooms, cafes, study areas, and more with an immersive virtual experience. Discover every corner of campus from wherever you are",
                0,
                1250,
                1350)); // WHITE

        items.add(new OnboardingItem(R.drawable.onboarding_image3,
                "Engage in Classroom Conversations",
                "Join discussions, ask questions, and collaborate with classmates on course topics. Stay connected.",
                0,
                1250,
                1350)); // WHITE

        adapter = new OnboardingItemAdapter(items);
        onboardingViewPager.setAdapter(adapter);
    }

    private void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
