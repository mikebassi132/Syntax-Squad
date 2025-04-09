package com.example.schoolboardapp;

public class Course {
    private String code;
    private String title;
    private String time;
    private String description;
    private int imageRes;

    public Course(String code, String title, String time, String description, int imageRes) {
        this.code = code;
        this.title = title;
        this.time = time;
        this.description = description;
        this.imageRes = imageRes;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public int getImageRes() {
        return imageRes;
    }
}
