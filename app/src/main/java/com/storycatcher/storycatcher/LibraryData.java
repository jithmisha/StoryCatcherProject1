package com.storycatcher.storycatcher;

public class LibraryData {
    String imageUrl;

    public LibraryData(){

    }

    public LibraryData(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }
}
