package com.mini.movie;

public class Movie {
    String title;
    int imgResource;

    public Movie(String title, int imgResource) {
        this.title = title;
        this.imgResource = imgResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
