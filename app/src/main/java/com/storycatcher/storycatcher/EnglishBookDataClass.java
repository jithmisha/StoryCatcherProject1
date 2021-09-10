package com.storycatcher.storycatcher;

public class EnglishBookDataClass {
    String imageUrl;
    String Title;

    public EnglishBookDataClass() {

    }

    public EnglishBookDataClass(String imageUrl, String title) {
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
