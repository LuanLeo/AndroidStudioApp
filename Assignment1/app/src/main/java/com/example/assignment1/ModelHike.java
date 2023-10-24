package com.example.assignment1;

public class ModelHike {
    private String Id, Name, Location, Date, Duration, Length, Difficulty, ParkingAva, FQuantity, Description;

    public ModelHike(String id, String name, String location, String date, String duration, String length, String difficulty, String parkingAva, String FQuantity, String description) {
        Id = id;
        Name = name;
        Location = location;
        Date = date;
        Duration = duration;
        Length = length;
        Difficulty = difficulty;
        ParkingAva = parkingAva;
        this.FQuantity = FQuantity;
        Description = description;
    }

    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }
    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }

    public String getDuration() {
        return Duration;
    }
    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getLength() {
        return Length;
    }
    public void setLength(String length) {
        Length = length;
    }

    public String getDifficulty() {
        return Difficulty;
    }
    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public String getParkingAva() {
        return ParkingAva;
    }
    public void setParkingAva(String parkingAva) {
        ParkingAva = parkingAva;
    }

    public String getFQuantity() {
        return FQuantity;
    }
    public void setFQuantity(String FQuantity) {
        this.FQuantity = FQuantity;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
}
