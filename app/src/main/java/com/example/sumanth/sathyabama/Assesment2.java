package com.example.sumanth.sathyabama;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Assesment2 extends AppCompatActivity {

    WebView web;
    String url="http://cloudportal.sathyabama.ac.in/cae/login.php";
    String one,two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment2);

        Bundle bn = getIntent().getExtras();
        String one = bn.getString("abc");

        Bundle bn2 = getIntent().getExtras();
        String two = bn2.getString("def");
        char[] myNameChars = two.toCharArray();

        two = String.valueOf(myNameChars);

        web=(WebView) findViewById(R.id.ase);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://cloudportal.sathyabama.ac.in/cae/login.php");

        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        final String js = "javascript:document.getElementById('regno').value='"+one+"';" +
                "document.getElementById('regno').click()";
        final String js2 = "javascript:document.getElementById('dob').value='"+two+"';" +
                "document.getElementById('btnLogin').click()";
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
        Toasty.info(Assesment2.this,"Press the back button twice quickly to go back",Toast.LENGTH_SHORT).show();

    }
}
