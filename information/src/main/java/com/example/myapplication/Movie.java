package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/10.
 */

public class Movie{
    private String original_title;
    private String summary;
    Map<String ,Object> rating;
    Map<String ,Object> images;
    private String[] genres;

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public Map<String, Object> getImages() {
        return images;
    }

    public void setImages(Map<String, Object> images) {
        this.images = images;
    }

    public Map<String, Object> getRating() {
        return rating;
    }

    public void setRating(Map<String, Object> rating) {
        this.rating = rating;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }




    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }
}
