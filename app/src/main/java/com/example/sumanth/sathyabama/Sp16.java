package com.example.sumanth.sathyabama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Sp16 extends AppCompatActivity {

    WebView web;
    String url = "http://206.189.140.85/b2016/cbssv2/sso/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp16);

        web = (WebView) findViewById(R.id.sixweb);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://206.189.140.85/b2016/cbssv2/sso/index.php");

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {

        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
