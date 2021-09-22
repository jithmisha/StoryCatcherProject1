package com.storycatcher.storycatcher;

public class SearchDataClass {
    String imageUrl;
    String Title;
    String URL;

    public SearchDataClass(String imageUrl, String title, String URL) {
        this.imageUrl = imageUrl;
        Title = title;
        this.URL = URL;
    }

    public SearchDataClass() {
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
