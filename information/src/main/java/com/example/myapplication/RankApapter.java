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
 * Created by Administrator on 2018/7/11.
 */

public class RankApapter extends BaseAdapter{
    private List<Map<String,Object>>data;
    private LayoutInflater layoutInflater;
    private Context context;



    public RankApapter(Context context, List<Map<String,Object>>data){
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
        View view=this.layoutInflater.inflate(R.layout.musicrank,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView5);
        Map<String ,Object>item=data.get(position);
        String imgeUrl=(String)item.get("imgurl");
        String imgurl =imgeUrl.replace("{size}","400");
        String rankname=(String)item.get("rankname");
        TextView textView=(TextView)view.findViewById(R.id.textView11);
        textView.setText(rankname);


        //数据赋值给组件显示
        Glide.with(context)
                .load(imgurl)
                .into(imageView);
        return view;
    }

}
