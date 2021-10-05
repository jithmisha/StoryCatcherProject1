package com.storycatcher.storycatcher;

public class User {

    String kidID,kidsName,kidsAge,picID,picUrl;


    public User() {

    }
    public User(String kidID, String kidsName, String kidsAge, String picUrl, String picID) {
        this.kidID = kidID;
        this.kidsName = kidsName;
        this.kidsAge = kidsAge;
        this.picUrl = picUrl;
        this.picID = picID;
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

    public String getPicID() {
        return picID;
    }

    public void setPicID(String picID) {
        this.picID = picID;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
