package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/6.
 */

public class DailyActivity extends Activity implements AdapterView.OnItemClickListener {
    TextView textView;
    ListView listView1;
    private final String TAG="JsonActivity";
    private NewsInfo newsInfo;

    private  String url="http://news-at.zhihu.com/api/2/news/latest";
    private OkHttpClient okHttpClient;
    private MyAdapter myAdapter;


    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            newsInfo=(NewsInfo)msg.obj;
            textView.setText(newsInfo.getDate());
            myAdapter=new MyAdapter(DailyActivity.this,newsInfo.getNews());
            listView1.setAdapter(myAdapter);
            /*simpleAdapter=new SimpleAdapter(JsonActivity.this,newsInfo.getNews(),R.layout.list_item,new String[]{"title"},
                    new int[]{R.id.textView4});

            listView1.setAdapter(simpleAdapter);
*/

        }
    };
    protected  void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);//设置布局文件
        setContentView(R.layout.json);
        textView=(TextView) findViewById(R.id.textView3);
        listView1=(ListView)findViewById(R.id.listview1);
        listView1.setOnItemClickListener(this);
        this.get();

    }
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
                NewsInfo newsInfo=
                        (NewsInfo)JsonUitl.stringToObject(json,NewsInfo.class);
                Log.d(TAG,newsInfo.getDate());
                Log.d(TAG,newsInfo.getNews()+" ");//只有主线程能更新UI

                Message message=new Message();
                message.obj=newsInfo;
                handler.sendMessage(message);

            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Map<String,Object> item = newsInfo.getNews().get(position);
        Toast.makeText(this,"点击了"+item.get("share_url"),Toast.LENGTH_SHORT).show();
        Intent intent=new Intent();
        intent.setClass(this,NewsDetailActivity.class);
        Bundle bundle=new Bundle();
        String shareUrl =(String)item.get("share_url");
        bundle.putString("shareUrl",shareUrl);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
