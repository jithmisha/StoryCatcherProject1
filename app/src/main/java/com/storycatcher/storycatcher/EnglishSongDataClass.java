package com.storycatcher.storycatcher;

public class EnglishSongDataClass {
    String imageUrl;
    String Title;

    public EnglishSongDataClass() {

    }

    public EnglishSongDataClass(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        Title = title;
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
}
