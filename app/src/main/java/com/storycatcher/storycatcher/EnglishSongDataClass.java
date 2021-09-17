package com.storycatcher.storycatcher;

public class EnglishSongDataClass {
    String imageUrl;
    String Title;
    String URL;

    public EnglishSongDataClass() {

    }

    public EnglishSongDataClass(String imageUrl, String title,String URL) {
        this.imageUrl = imageUrl;
        Title = title;
        this.URL=URL;
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
}
