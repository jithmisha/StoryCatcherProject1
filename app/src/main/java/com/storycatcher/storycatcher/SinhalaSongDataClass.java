package com.storycatcher.storycatcher;

public class SinhalaSongDataClass {
    String imageUrl;
    String Title;

    public SinhalaSongDataClass() {
    }

    public SinhalaSongDataClass(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.Title = name;
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
}
