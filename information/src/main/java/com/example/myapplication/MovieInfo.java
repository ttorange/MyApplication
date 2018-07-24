package com.example.myapplication;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/9.
 */

public class MovieInfo {
    private String[] genres;


    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    private List<Map<String,Object>>subjects;


    public List<Map<String, Object>> getMovie() {
        return subjects;
    }

    public void setMovie(List<Map<String, Object>> subjects) {
        this.subjects = subjects;
    }

    List<Map<String,Object>> casts;

    public List<Map<String, Object>> getCasts() {
        return casts;
    }

    public void setCasts(List<Map<String, Object>> casts) {
        this.casts = casts;
    }

}
