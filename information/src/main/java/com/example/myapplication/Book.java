package com.example.myapplication;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/12.
 */

public class Book {
    private String[] author;
    private String summary;
    Map<String ,Object> rating;
    String title;
    String bublisher;

    public String getBublisher() {
        return bublisher;
    }

    public void setBublisher(String bublisher) {
        this.bublisher = bublisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Map<String, Object> getRating() {
        return rating;
    }

    public void setRating(Map<String, Object> rating) {
        this.rating = rating;
    }

    public Map<String, Object> getImages() {
        return images;
    }

    public void setImages(Map<String, Object> images) {
        this.images = images;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    Map<String ,Object> images;
    private String original_title;

}
