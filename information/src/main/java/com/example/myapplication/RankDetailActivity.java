package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/11.
 */

public class RankDetailActivity extends Activity implements AdapterView.OnItemClickListener{
    private final String TAG="RankDetailActivity";
    private String url="http://m.kugou.com/rank/info/?rankid=6666&page=1&json=true+";
    private RankInfo rankInfo;
    private SongsAdapter songsAdapter;

    private TextView textView;
    private ImageView imageView;
    private ListView listView;

    private OkHttpClient okHttpClient;


    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String rankid =bundle.getString("rankid");
        url=url.replace("6666",rankid);
        this.get();

        setContentView(R.layout.song);
        textView=(TextView) findViewById(R.id.textView13);
       listView=(ListView)findViewById(R.id.listview3);
        imageView=(ImageView)findViewById(R.id.imageView6);
        listView.setOnItemClickListener(this);


    }
    private Context context;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){

          rankInfo=(RankInfo) msg.obj;
            Map<String, Object> songs = (Map<String, Object>)rankInfo.getSongs();
            List<Map<String, Object>> list = (List<Map<String, Object>>)songs.get("list");
            songsAdapter = new SongsAdapter(RankDetailActivity.this, list);
            listView.setAdapter(songsAdapter);

            Map<String, Object> info = (Map<String, Object>)rankInfo.getInfo();
            String title=(String)info.get("rankname");
            textView.setText(title);
            String imgeUrl=(String)info.get("imgurl");
            String imgurl =imgeUrl.replace("{size}","400");

            Glide.with(RankDetailActivity.this)
                    .load(imgurl)
                    .into(imageView);


        }
    };

    public void get(){
        okHttpClient=new OkHttpClient();

        Request.Builder builder=new Request.Builder();
        Request request= builder.get().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            //访问失败的回调函数
            public void onFailure(Call call, IOException e) {
                Log.d(TAG,"访问失败");
            }

            @Override
            //访问成功的回调函数
            public void onResponse(Call call, Response response) throws IOException {
                String json =response.body().string();
                Log.d(TAG,json);
                rankInfo=
                        (RankInfo) JsonUitl.stringToObject(json,RankInfo.class);
                //Log.d(TAG,movieInfo.getData());
                //Log.d(TAG,movie.getMovie()+" ");//只有主线程能更新UI

                Message message=new Message();
                message.obj=rankInfo;
                handler.sendMessage(message);

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> songs = (Map<String, Object>)rankInfo.getSongs();
        List<Map<String, Object>> list = (List<Map<String, Object>>) songs.get("list");
        Map<String,Object> item = list.get(position);
        String hash=(String)item.get("hash");

        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("hash",hash);
        intent.putExtras(bundle);
        intent.setClass(this,SongDetailActivity.class);
        startActivity(intent);

    }
}
