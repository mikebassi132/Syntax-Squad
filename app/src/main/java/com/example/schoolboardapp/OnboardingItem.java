package com.example.schoolboardapp;

public class OnboardingItem {
    private final int image;
    private final String title;
    private final String description;
    private final int backgroundType;
    private final int imageWidth;
    private final int imageHeight;

    public OnboardingItem(int image, String title, String description, int backgroundType, int imageWidth, int imageHeight) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.backgroundType = backgroundType;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public int getImage() { return image; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getBackgroundType() { return backgroundType; }
    public int getImageWidth() { return imageWidth; }
    public int getImageHeight() { return imageHeight; }
}
