package com.storycatcher.storycatcher;

public class CreateProfileClass {
    public String kidID, kidsName, parentID,kidsAge,picID,picUrl;

    public CreateProfileClass() {
    }

    public CreateProfileClass(String kidID, String kidsName,String kidsAge, String parentID, String picUrl, String picID) {
        this.kidID = kidID;
        this.kidsName = kidsName;
        this.parentID = parentID;
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

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
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
