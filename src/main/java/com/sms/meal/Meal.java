package com.sms.meal;

public class Meal {
    private final String id;
    private final String name;
    private final String description;
    private final String location;
    private final String owner;

    public Meal(String id, String name, String description, String location) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.owner = null;
    }

    public Meal(String id, String name, String description, String location, String owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }
}
