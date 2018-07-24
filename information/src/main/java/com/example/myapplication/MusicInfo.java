package com.example.myapplication;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/11.
 */

public class MusicInfo {
    private Map<String,Object> rank;
    private List<Map<String,Object>> list;
    String rankname;
    int rankid;

    public int getRankid() {
        return rankid;
    }

    public void setRankid(int rankid) {
        this.rankid = rankid;
    }

    public String getRankname() {
        return rankname;
    }

    public void setRankname(String rankname) {
        this.rankname = rankname;
    }

    public List<Map<String, Object>> getList() {
        getRank();
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public Map<String, Object> getRank() {
        return rank;
    }

    public void setRank(Map<String, Object> rank) {
        this.rank = rank;
    }
}
