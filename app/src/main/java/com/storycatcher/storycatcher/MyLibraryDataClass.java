package com.storycatcher.storycatcher;

public class MyLibraryDataClass {
    String imageUrl;
    String Title;
    String URL;
    String ID;

    public MyLibraryDataClass(String imageUrl, String title, String URL, String ID) {
        this.imageUrl = imageUrl;
        Title = title;
        this.URL = URL;
        this.ID = ID;
    }

    public MyLibraryDataClass() {
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
