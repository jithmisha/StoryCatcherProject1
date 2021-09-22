package com.storycatcher.storycatcher;

public class User {

    String kidID,kidsName,kidsAge;


    public User() {

    }
    public User(String kidID, String kidsName, String kidsAge) {
        this.kidID = kidID;
        this.kidsName = kidsName;
        this.kidsAge = kidsAge;
    }

    public String getKidID() {
        return kidID;
    }

    public void setKidID(String kidID) {
        this.kidID = kidID;
    }

    public String getKidsName() {
        return kidsName;
    }

    public void setKidsName(String kidsName) {
        this.kidsName = kidsName;
    }

    public String getKidsAge() {
        return kidsAge;
    }

    public void setKidsAge(String kidsAge) {
        this.kidsAge = kidsAge;
    }
}
