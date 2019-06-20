package com.example.sumanth.sathyabama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Chat extends AppCompatActivity {

    WebView chatweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatweb=(WebView) findViewById(R.id.chatweb);
        chatweb.setWebViewClient(new WebViewClient());
        chatweb.loadUrl("https://tlk.io/sathyabama");

        WebSettings webSettings=chatweb.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }
    @Override
    public void onBackPressed() {

        if(chatweb.canGoBack())
        {
            chatweb.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

}