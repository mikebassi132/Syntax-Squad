package com.example.schoolboardapp;

public class AppointmentTime {
    private String time;
    private boolean isAvailable;

    public AppointmentTime(String time, boolean isAvailable) {
        this.time = time;
        this.isAvailable = isAvailable;
    }

    public String getTime() {
        return time;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
