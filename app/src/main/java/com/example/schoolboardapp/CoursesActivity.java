package com.example.schoolboardapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.ImageView;


public class CoursesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());


        recyclerView = findViewById(R.id.coursesRecyclerView);

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CIS 341", "System Administration", "Monday Evening",
                "This course provides a practical foundation in system administration with a focus on Linux-based systems. Topics include user and group management, file system organization, process control, system performance monitoring, backup strategies, and basic shell scripting. Students gain hands-on experience in configuring, maintaining, and troubleshooting systems in a secure and efficient manner.\n" +
                        "\n", R.drawable.img_courses));
        courses.add(new Course("Comp 251", "Data Structures and Algorithms", "Thursday Morning",
                "This course introduces essential data structures such as arrays, linked lists, stacks, queues, trees, and graphs, along with key algorithms for searching, sorting, and traversal. Emphasis is placed on algorithm analysis, time and space complexity, and practical implementation using programming languages like Java or Python.", R.drawable.img_courses));
        courses.add(new Course("CMNS 311", "Investigative Reporting", "Thursday Evening",
                "This course explores investigative journalism techniques, including research methods, interviewing strategies, source verification, and ethical considerations. Students learn to uncover stories, analyze public records, and produce compelling, fact-driven reports that hold institutions accountable.", R.drawable.img_courses));
        courses.add(new Course("CIS 262", "Networking Basics", "Friday Morning",
                "This course introduces fundamental concepts in computer networking, including network topologies, protocols, IP addressing, and the OSI and TCP/IP models. Students gain an understanding of how data is transmitted across networks, with practical lab work focused on configuring routers, switches, and networked devices.", R.drawable.img_courses));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CourseAdapter(courses, this));
    }
}
