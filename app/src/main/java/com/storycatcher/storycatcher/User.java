package com.storycatcher.storycatcher;

public class User {

    String kidID,kidsName;
    int kidsAge;

    public User() {

    }
    public User(String kidID, String kidsName, int kidsAge) {
        this.kidID = kidID;
        this.kidsName = kidsName;
        this.kidsAge = kidsAge;
    }
    /*public User(String kidName){
        this.kidsName=getKidsName();
    }*/
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

    public int getKidsAge() {
        return kidsAge;
    }

    public void setKidsAge(int kidsAge) {
        this.kidsAge = kidsAge;
    }
}
