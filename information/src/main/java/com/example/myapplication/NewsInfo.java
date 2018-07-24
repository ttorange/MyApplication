package com.example.myapplication;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/4.
 */

public class NewsInfo {
    private String date;
    private List<Map<String,Object>> news;

    public String getDate() {
            return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Map<String, Object>> getNews() {
        return news;
    }

    public void setNews(List<Map<String, Object>> news) {
        this.news = news;
    }
}
