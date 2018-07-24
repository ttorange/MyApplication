package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/12.
 */

public class BookDetailActivity extends Activity {
    private final String TAG="MovieDetailActivity";
    private String url="https://api.douban.com/v2/book/1791390";

    private Book bookInfo;

    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    private ImageView imageView;
    private OkHttpClient okHttpClient;


    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String id =bundle.getString("id");
        url=url.replace("1791390",id);
        this.get();

        setContentView(R.layout.movie);
        textView=(TextView) findViewById(R.id.textView7);
        textView1=(TextView) findViewById(R.id.textView8);
        textView2=(TextView) findViewById(R.id.textView9);
        textView3=(TextView)findViewById(R.id.textView10) ;
        imageView=(ImageView)findViewById(R.id.imageView4);


    }
    private Context context;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            bookInfo=(Book) msg.obj;
            String title =bookInfo.getTitle();
           /* String publisher=bookInfo.getBublisher();*/
            textView.setText(title);
            String s=bookInfo.getSummary();
            textView3.setText(s);
            Map<String ,Object> rating=(Map<String, Object>)bookInfo.getRating();
           String averager =(String) rating.get("average");
            textView2.setText("评分"+averager);
            Map<String,Object> imageMap =(Map<String,Object>)bookInfo.getImages();
            String imageUrl=(String)imageMap.get("small");
            Glide.with(BookDetailActivity.this)
                    .load(imageUrl)
                    .into(imageView);
            String g=" ";
            String[] genres=(String[])bookInfo.getAuthor();
            for(int i=0;i<genres.length;i++){
                g+=genres[i]+"   ";
            }
            textView1.setText(g+"\n");

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
                bookInfo=
                        (Book) JsonUitl.stringToObject(json,Book.class);
                //Log.d(TAG,movieInfo.getData());
                //Log.d(TAG,movie.getMovie()+" ");//只有主线程能更新UI

                Message message=new Message();
                message.obj=bookInfo;
                handler.sendMessage(message);

            }
        });
    }
}
