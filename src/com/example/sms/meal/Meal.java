package com.example.sms.meal;

public class Meal {
    private final String id;
    private final String name;
    private final String description;
    private final String location;
    private boolean booked;

    public Meal(String id, String name, String description, String location) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public boolean isBooked() {
        return booked;
    }
}
