package com.storycatcher.storycatcher;

public class SongsDataClass {
    String imageUrl;
    String name;

    public SongsDataClass() {
    }

    public SongsDataClass(String imageUrl, String name) {
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
