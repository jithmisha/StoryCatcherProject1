package com.storycatcher.storycatcher;

public class ProfilePictureDataClass {

    String picUrl,picID;

    public ProfilePictureDataClass() {
    }

    public ProfilePictureDataClass(String picUrl, String picID) {
        this.picUrl = picUrl;
        this.picID = picID;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicID() {
        return picID;
    }

    public void setPicID(String picID) {
        this.picID = picID;
    }
}
