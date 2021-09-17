package com.storycatcher.storycatcher;

public class EnglishBookDataClass {
    String imageUrl;
    String Title;
    String URL;

    public EnglishBookDataClass() {

    }

    public EnglishBookDataClass(String imageUrl, String title,String URL) {
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
