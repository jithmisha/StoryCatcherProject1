package com.storycatcher.storycatcher;

public class LibraryDataClass {
    String imageUrl;
    String title;

    public LibraryDataClass() {

    }
    public LibraryDataClass(String imageUrl) {
        this.imageUrl = imageUrl;
        this.title=title;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
