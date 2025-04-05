package com.example.schoolboardapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class EventsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Button enroll1 = findViewById(R.id.enrollButton);
        enroll1.setOnClickListener(v -> openCalendar(2025, 4, 10, 14, 0));  // May 10, 2025 at 2:00 PM
    }

    private void openCalendar(int year, int month, int day, int hour, int minute) {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(year, month - 1, day, hour, minute);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(Uri.parse("content://com.android.calendar/events"))
                .putExtra("beginTime", beginTime.getTimeInMillis())
                .putExtra("allDay", false)
                .putExtra("title", "UFV Event")
                .putExtra("description", "You're enrolled in a UFV event!")
                .putExtra("eventLocation", "UFV Campus");

        startActivity(intent);
    }
}
