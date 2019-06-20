package com.example.sumanth.sathyabama;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Temporary_webview extends AppCompatActivity {

    WebView webView;
    String code;
    String url ="http://www.sathyabama.ac.in/classnotes_list.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary_webview);

        Bundle bn = getIntent().getExtras();
        String code = bn.getString("codes");

        webView=(WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.sathyabama.ac.in/classnotes_list.php");

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        final String js = "javascript:document.getElementById('search_text').value='"+code+"';" +
                "document.getElementByClassName('regLog-btn3').click()";
        webView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19) {
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });

                }

            }
        });

        }

    @Override
    public void onBackPressed() {

        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }

    }
}
