package com.storycatcher.storycatcher;

public class PoemDataClass {
    String imageUrl;
    String name;

    public PoemDataClass() {
    }

    public PoemDataClass(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
