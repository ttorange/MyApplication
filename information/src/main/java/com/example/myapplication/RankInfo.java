package com.example.myapplication;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/11.
 */

public class RankInfo {
    private Map<String,Object> info;
    private Map<String,Object> songs;

    public Map<String, Object> getInfo(){
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public Map<String, Object> getSongs() {
        return songs;
    }

    public void setSongs(Map<String, Object> songs) {
        this.songs = songs;
    }
}
