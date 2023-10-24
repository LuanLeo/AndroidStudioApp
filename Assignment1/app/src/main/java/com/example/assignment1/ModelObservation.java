package com.example.assignment1;

public class ModelObservation {
    private String Id, Name, Date, Comment, HikeId;

    public ModelObservation(String id, String name, String date, String comment, String hikeId) {
        Id = id;
        Name = name;
        Date = date;
        Comment = comment;
        HikeId = hikeId;
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

    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }

    public String getComment() {
        return Comment;
    }
    public void getComment(String comment) {
        Comment = comment;
    }

    public String getHikeId() {
        return HikeId;
    }
    public void setHikeId(String hikeId) {
        HikeId = hikeId;
    }
}
