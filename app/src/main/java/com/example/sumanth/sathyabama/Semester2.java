package com.example.sumanth.sathyabama;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Semester2 extends AppCompatActivity {

    WebView web;
    String url="http://www.sathyabama.ac.in/result.php";
    String three,four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester2);

        Bundle bn = getIntent().getExtras();
        String three = bn.getString("ghi");

        Bundle bn2 = getIntent().getExtras();
        String four = bn2.getString("jkl");

        web=(WebView) findViewById(R.id.sem);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://www.sathyabama.ac.in/result.php");

        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        final String js = "javascript:document.getElementById('Regnum').value='"+three+"';" +
                "document.getElementById('Regnum').click()";
        final String js2 = "javascript:document.getElementById('stu_dob').value='"+four+"';" +
                "document.getElementByClassName('sub-btn')[0].click()";
// Set a web view client for web view
        web.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                    view.evaluateJavascript(js2, new ValueCallback<String>() {
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

        if(web.canGoBack())
        {
            web.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
