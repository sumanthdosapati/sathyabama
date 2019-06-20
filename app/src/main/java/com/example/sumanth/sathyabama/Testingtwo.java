package com.example.sumanth.sathyabama;

import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;

public class Testingtwo extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    WebView webView;
    String url="http://cloudportal.sathyabama.ac.in/cae/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testingtwo);

        TextView one = findViewById(R.id.regt);
        Bundle bn = getIntent().getExtras();
        String firs = bn.getString("abc");
        one.setText(String.valueOf(firs));

        TextView two = findViewById(R.id.dobt);
        Bundle bn2 = getIntent().getExtras();
        String secon = bn.getString("abc");
        two.setText(String.valueOf(secon));

        webView = (WebView) findViewById(R.id.test2);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://cloudportal.sathyabama.ac.in/cae/login.php");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        final String js = "javascript:document.getElementById('regno').value='"+one+"';" +
                "document.getElementById('regno').click()";
        webView.loadUrl("javascript: (function(){document.getElementById('regno').value ='"+one+"';})();");
        webView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view, String url){
                if(Build.VERSION.SDK_INT >= 19){
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                        }
                    });
                }
            }
        });


        final String js2 = "javascript:document.getElementById('dob').value='12-02-1999';" +
                "document.getElementById('btnLogin').click()";
    webView.loadUrl("javascript: (function(){document.getElementById('dob').value ='"+two+"';})();");
                webView.setWebViewClient(new WebViewClient(){

        public void onPageFinished(WebView view, String url){
            if(Build.VERSION.SDK_INT >= 19){
                view.evaluateJavascript(js, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {
                    }
                });
            }
        }
    });

}



    }