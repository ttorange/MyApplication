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
 * Created by Administrator on 2018/7/12.
 */

public class BookAdapter extends BaseAdapter {
    private List<Map<String,Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    private MovieInfo movieInfo;



    public BookAdapter(Context context, List<Map<String,Object>>data){
        this.context=context;
        this.data=data;
        this.layoutInflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //根据布局文件生成view对象
        View view=this.layoutInflater.inflate(R.layout.movie_item,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView3);
        TextView textView=(TextView)view.findViewById(R.id.textView4);
        TextView textView1=(TextView)view.findViewById(R.id.textView5);
        TextView textView2=(TextView)view.findViewById(R.id.textView6);
        Map<String ,Object>item=data.get(position);

        Map<String,Object> imageMap =(Map<String,Object>)item.get("images");
        String imageUrl=(String)imageMap.get("large");
        String title =(String)item.get("title");
        Map<String ,Object>rating=(Map<String, Object>)item.get("rating");
        String averager =(String) rating.get("average");
        String mainland_pubdate=(String)item.get("publisher");
       /* String author[]=(String[])item.get("author");
        String g=" ";
        for(int i=0;i<author.length;i++){
            g+=author[i];
        }*/

        /*String cast=(String)item.get("casts");
        for((Map<String,Object>)item.getCasts("casts")){
            String name=(String)item.get("name");
            cast=cast+name+" ";
        }*/

        //数据赋值给组件显示
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
        textView.setText(title);
        textView1.setText("评分："+averager);
        textView2.setText("出版社:"+mainland_pubdate);



        return view;
    }

}
