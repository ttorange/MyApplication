package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.WebView;
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
 * Created by Administrator on 2018/7/9.
 */

public class MovieDetailActivity extends Activity {
    private final String TAG="MovieDetailActivity";
    private String url="http://api.douban.com/v2/movie/subject/";
    private MovieInfo movieInfo;
    private Movie movie;

    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private ImageView imageView;

    private MovieAdapter movieAdapter;
    private OkHttpClient okHttpClient;


    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String id =bundle.getString("id");
         url+=id;
        this.get();

        setContentView(R.layout.movie);
        textView=(TextView) findViewById(R.id.textView7);
        textView1=(TextView) findViewById(R.id.textView8);
        textView2=(TextView) findViewById(R.id.textView9);
        textView3=(TextView) findViewById(R.id.textView10);
        imageView=(ImageView)findViewById(R.id.imageView4);


    }
    private Context context;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
           movie=(Movie)msg.obj;
            String title =movie.getOriginal_title();
            textView.setText(title);
            String s=movie.getSummary();
            textView3.setText(s);
            Map<String ,Object> rating=(Map<String, Object>)movie.getRating();
            Double averager =(Double)rating.get("average");
            textView2.setText("评分"+averager.toString());
            Map<String,Object> imageMap =(Map<String,Object>)movie.getImages();
            String imageUrl=(String)imageMap.get("small");
            Glide.with(MovieDetailActivity.this)
                    .load(imageUrl)
                    .into(imageView);
            String g=" ";
            String[] genres=(String[])movie.getGenres();
            for(int i=0;i<genres.length;i++){
                g+=genres[i]+"/";
            }
            textView1.setText(g);

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
                movie=
                        (Movie) JsonUitl.stringToObject(json,Movie.class);
                //Log.d(TAG,movieInfo.getData());
                //Log.d(TAG,movie.getMovie()+" ");//只有主线程能更新UI

                Message message=new Message();
                message.obj=movie;
                handler.sendMessage(message);

            }
        });
    }
}
