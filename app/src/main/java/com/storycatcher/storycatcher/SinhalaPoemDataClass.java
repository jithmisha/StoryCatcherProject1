package com.storycatcher.storycatcher;

public class SinhalaPoemDataClass {
    String imageUrl;
    String Title;
    String URL;

    public SinhalaPoemDataClass() {

    }
    public SinhalaPoemDataClass(String imageUrl, String Title, String URL) {
        this.imageUrl = imageUrl;
        this.Title = this.Title;
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
        this.Title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
