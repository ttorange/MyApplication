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

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/11.
 */

public class MusicActivity extends Activity implements AdapterView.OnItemClickListener {

    ListView listView1;
    private final String TAG = "MusicActivity";
    private MusicInfo musicInfo;

    private String url = "http://m.kugou.com/rank/list&json=true";
    private OkHttpClient okHttpClient;
    private RankApapter rankApapter;


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            musicInfo = (MusicInfo) msg.obj;
            Map<String, Object> rank = (Map<String, Object>) musicInfo.getRank();
            List<Map<String, Object>> list = (List<Map<String, Object>>) rank.get("list");
            rankApapter = new RankApapter(MusicActivity.this, list);
            listView1.setAdapter(rankApapter);

            /*simpleAdapter=new SimpleAdapter(JsonActivity.this,newsInfo.getNews(),R.layout.list_item,new String[]{"title"},
                    new int[]{R.id.textView4});

            listView1.setAdapter(simpleAdapter);
*/

        }
    };

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);//设置布局文件
        setContentView(R.layout.movielist);
        listView1 = (ListView) findViewById(R.id.listview);
        listView1.setOnItemClickListener(this);
        this.get();

    }

    public void get() {
        okHttpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            //访问失败的回调函数
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "访问失败");
            }

            @Override
            //访问成功的回调函数
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d(TAG, json);
                MusicInfo musicInfo =
                        (MusicInfo) JsonUitl.stringToObject(json, MusicInfo.class);
                //Log.d(TAG,movieInfo.getData());
                Log.d(TAG, musicInfo.getRank() + " ");//只有主线程能更新UI

                Message message = new Message();
                message.obj = musicInfo;
                handler.sendMessage(message);

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Map<String, Object> rank = (Map<String, Object>) musicInfo.getRank();
        List<Map<String, Object>> list = (List<Map<String, Object>>) rank.get("list");
        Map<String,Object> item = list.get(position);
        double rankid=(Double) item.get("rankid");
        String sid = Double.toString(rankid);
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("rankid",sid);
        intent.putExtras(bundle);
        intent.setClass(this,RankDetailActivity.class);
        startActivity(intent);
    }
}
