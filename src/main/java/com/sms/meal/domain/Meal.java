package com.sms.meal.domain;

import android.os.Parcel;
import android.os.Parcelable;
public class Meal implements Parcelable {
    private final String id;
    private final String name;
    private final String description;
    private final String location;
    private final String owner;
    private byte[] image;

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

    public byte[] getImage(){
        return image;
    }

    public void setImage(byte[] image){
        this.image = image;
    }

    protected Meal(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        location = in.readString();
        owner = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(location);
        dest.writeString(owner);
    }

    public static final Parcelable.Creator<Meal> CREATOR = new Parcelable.Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal meal = (Meal) o;

        if (description != null ? !description.equals(meal.description) : meal.description != null)
            return false;
        if (id != null ? !id.equals(meal.id) : meal.id != null) return false;
        if (location != null ? !location.equals(meal.location) : meal.location != null)
            return false;
        if (name != null ? !name.equals(meal.name) : meal.name != null) return false;
        if (owner != null ? !owner.equals(meal.owner) : meal.owner != null) return false;

        return true;
    }

}
