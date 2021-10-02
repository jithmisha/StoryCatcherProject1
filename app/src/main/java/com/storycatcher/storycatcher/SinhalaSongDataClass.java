package com.storycatcher.storycatcher;

public class SinhalaSongDataClass {
    String imageUrl;
    String Title;
    String URL;
    String ID;

    public SinhalaSongDataClass() {
    }

    public SinhalaSongDataClass(String imageUrl, String name,String URL, String ID) {
        this.imageUrl = imageUrl;
        this.Title = name;
        this.URL = URL;
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
