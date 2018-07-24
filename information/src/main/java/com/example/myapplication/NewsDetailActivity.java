package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Administrator on 2018/7/5.
 */

public class NewsDetailActivity extends Activity {
    private WebView webView;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.news_detail);
        webView=(WebView)findViewById(R.id.webview);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String shareUrl =bundle.getString("shareUrl");
        webView.loadUrl(shareUrl);
    }
}
