package com.storycatcher.storycatcher;

public class EnglishSongDataClass {
    String imageUrl;
    String Title;
    String URL;
    String ID;

    public EnglishSongDataClass() {

    }

    public EnglishSongDataClass(String imageUrl, String title,String URL, String ID) {
        this.imageUrl = imageUrl;
        Title = title;
        this.URL=URL;
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
        Title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
