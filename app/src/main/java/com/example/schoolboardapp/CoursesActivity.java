package com.example.schoolboardapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CourseAdapter courseAdapter;
    ArrayList<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        recyclerView = findViewById(R.id.recyclerCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseList = new ArrayList<>();
        courseList.add(new Course("COMP-370-ON1", "Ao Ao Feng"));
        courseList.add(new Course("DEMO-202", "Dr. Sample Name"));
        courseList.add(new Course("MATH-303", "Jane Doe"));
        courseList.add(new Course("TEST-101", "Prof. Placeholder"));

        courseAdapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(courseAdapter);
    }
}
