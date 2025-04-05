package com.example.schoolboardapp;

public class Course {
    String code, instructor;

    public Course(String code, String instructor) {
        this.code = code;
        this.instructor = instructor;
    }

    public String getCode() {
        return code;
    }

    public String getInstructor() {
        return instructor;
    }
}

