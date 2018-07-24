package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private GridView gridView;
    private List<Map<String,Object>> data;

    private  int[] icons={R.drawable.m2,R.drawable.m1,R.drawable.s1,R.drawable.s2,R.drawable.n2,R.drawable.n1};
    private String[] title ={"热映电影","即将上映","音乐","音乐排行","图书","日报"};
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=(GridView)findViewById(R.id.gridview);
        data=init();
        simpleAdapter=new SimpleAdapter(this,data,R.layout.gridview,
                new String[]{"icon","title"},
                new int[]{R.id.imageView,R.id.textView});
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
    }
    public List<Map<String,Object>>init(){
        List<Map<String,Object>> result =new ArrayList<Map<String, Object>>();
        for(int i=0;i<icons.length;i++){
            Map<String,Object> hm=new HashMap<String,Object>();
            hm.put("icon",icons[i]);
            hm.put("title",title[i]);
            result.add(hm);
        }
        return  result;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
        switch (position){
            case 0:
                intent.setClass(this,MovieActivity.class);
                startActivity(intent);
                break;

            case 1:
                intent.setClass(this,CmovieActivity.class);
                startActivity(intent);

                break;
            case 2:
                intent.setClass(this,MusicActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent.setClass(this,MusicActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent.setClass(this,BookActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent.setClass(this,DailyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
