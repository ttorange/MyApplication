
package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/*Created by Administrator on 2018/7/11.*/



public class SongDetailActivity extends Activity {
    private ImageView imageView;
    private TextView textView;
    private OkHttpClient okHttpClient;
    SongsInfo songsInfo;
    private final String TAG="RankDetailActivity";
    private String url="http://m.kugou.com/app/i/getSongInfo.php?cmd=playInfo&hash=CB7EE97F4CC11C4EA7A1FA4B516A5D97";

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.song_player);
        imageView=(ImageView)findViewById(R.id.imageView7);
        textView=(TextView)findViewById(R.id.textView14);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String hash =bundle.getString("hash");
        url=url.replace("CB7EE97F4CC11C4EA7A1FA4B516A5D97",hash);
        this.get();

    }

    private Context context;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){

            songsInfo=(SongsInfo) msg.obj;
            /*String song=songsInfo.getUrl();*/
            String album=(String)songsInfo.getAlbum_img();
            album=album.replace("{size}","400");
            Glide.with(SongDetailActivity.this)
                    .load(album)
                    .into(imageView);
            String title=(String)songsInfo.getFileName();
            textView.setText(title);

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
                songsInfo=
                        (SongsInfo) JsonUitl.stringToObject(json,SongsInfo.class);
                //Log.d(TAG,movieInfo.getData());
                //Log.d(TAG,movie.getMovie()+" ");//只有主线程能更新UI

                Message message=new Message();
                message.obj=songsInfo;
                handler.sendMessage(message);

            }
        });
    }

}
