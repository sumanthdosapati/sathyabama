package com.example.sumanth.sathyabama;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class CommonWebview extends AppCompatActivity {

    String url;
    WebView webView;
    final Activity webViewActivity=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_webview);


        Intent intent = this.getIntent();

        if (intent != null) {

            Bundle data = getIntent().getExtras();

            if (data.containsKey("newslink")) {
                url = data.getString("newslink");
                webView = (WebView) findViewById(R.id.commonwebview);
                webView.clearCache(true);
                webView.clearHistory();
                webView.getSettings().setJavaScriptEnabled(true);
            }

            if (data.containsKey("sportslink")) {
                url = data.getString("sportslink");
                webView = (WebView) findViewById(R.id.commonwebview);
                webView.clearCache(true);
                webView.clearHistory();
                webView.getSettings().setJavaScriptEnabled(true);
            }

            if (data.containsKey("eventslink")) {
                url = data.getString("eventslink");
                webView = (WebView) findViewById(R.id.commonwebview);
                webView.clearCache(true);
                webView.clearHistory();
                webView.getSettings().setJavaScriptEnabled(true);
            }

            webView.loadUrl(url);


        }
    }
}
