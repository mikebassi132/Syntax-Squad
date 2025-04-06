package com.example.schoolboardapp;

public class ServiceItem {
    private final int imageRes;
    private final String title;
    private final String description;
    private final String location;
    private final String bookingLink;

    public ServiceItem(int imageRes, String title, String description, String location, String bookingLink) {
        this.imageRes = imageRes;
        this.title = title;
        this.description = description;
        this.location = location;
        this.bookingLink = bookingLink;
    }

    public int getImageRes() { return imageRes; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getBookingLink() { return bookingLink; }
}
