package com.example.myapplication;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/12.
 */

public class BookInfo {
    List<Map<String,Object>>books;
    private String original_title;
    private String summary;
    private  Map<String ,Object> rating;
    private Map<String ,Object> images;


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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Map<String, Object>> getBooks() {
        return books;
    }

    public void setBooks(List<Map<String, Object>> books) {
        this.books = books;
    }

   private String publisher;
   private String[] author;



    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }
}
