package com.example.schoolboardapp;

public class GradCourse {
    public String name;
    public String grade;      // used in course history
    public String instructor; // used in current semester
    public int credits;

    // ✅ Required no-arg constructor for Firebase
    public GradCourse() {}

    // Used for course history (has grade)
    public GradCourse(String name, String grade, int credits, boolean isHistory) {
        this.name = name;
        this.grade = grade;
        this.credits = credits;
    }

    // Used for current semester (has instructor)
    public GradCourse(String name, int credits, String instructor) {
        this.name = name;
        this.credits = credits;
        this.instructor = instructor;
    }
}
