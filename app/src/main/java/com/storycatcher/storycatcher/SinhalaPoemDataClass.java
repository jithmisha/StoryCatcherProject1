package com.storycatcher.storycatcher;

public class SinhalaPoemDataClass {
    String imageUrl;
    String Title;

    public SinhalaPoemDataClass() {
    }

    public SinhalaPoemDataClass(String imageUrl, String name) {
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
