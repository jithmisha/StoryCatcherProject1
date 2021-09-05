package com.storycatcher.storycatcher;

public class LibraryDataClass {
    String imageUrl;
    String Title;

    public LibraryDataClass() {

    }
    public LibraryDataClass(String imageUrl, String Title) {
        this.imageUrl = imageUrl;
        this.Title = this.Title;
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
