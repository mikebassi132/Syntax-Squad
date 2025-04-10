package com.example.schoolboardapp;
public class AppointmentOption {
    private String professorName;
    private String availableTime;

    public AppointmentOption(String professorName, String availableTime) {
        this.professorName = professorName;
        this.availableTime = availableTime;
    }

    public String getProfessorName() {
        return professorName;
    }

    public String getAvailableTime() {
        return availableTime;
    }
}

