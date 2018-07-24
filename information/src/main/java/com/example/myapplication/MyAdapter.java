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
 * Created by Administrator on 2018/7/5.
 */

public class MyAdapter extends BaseAdapter {

    private List<Map<String,Object>>data;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyAdapter(Context context, List<Map<String,Object>>data){
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
        View view=this.layoutInflater.inflate(R.layout.news_item,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView2);
        TextView textView=(TextView)view.findViewById(R.id.textView2);
        String image =(String)data.get(position).get("image");
        String title =(String)data.get(position).get("title");
        //数据赋值给组件显示
        Glide.with(context)
                .load(image)
                .into(imageView);
        textView.setText(title);
        return view;
    }
}
