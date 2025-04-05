package com.example.schoolboardapp;

public class HomeCardItem {
    private final int imageRes;
    private final String title;
    private final String description;
    private final int labelColor;

    public HomeCardItem(int imageRes, String title, String description, int labelColor) {
        this.imageRes = imageRes;
        this.title = title;
        this.description = description;
        this.labelColor = labelColor;
    }

    public int getImageRes() { return imageRes; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getLabelColor() { return labelColor; }
}
