package com.example.sumanth.sathyabama;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Assesment extends Fragment{

    WebView webView;
    String url="http://cloudportal.sathyabama.ac.in/cae/login.php";
    String one,two;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.assesment, container, false);

        Bundle bn = getActivity().getIntent().getExtras();
        String one = bn.getString("abc");

        Bundle bn2 = getActivity().getIntent().getExtras();
        String two = bn2.getString("def");

        webView=(WebView)rootView.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://cloudportal.sathyabama.ac.in/cae/login.php");

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        final String js = "javascript:document.getElementById('regno').value='"+one+"';" +
                "document.getElementById('regno').click()";
        final String js2 = "javascript:document.getElementById('dob').value='"+two+"';" +
                "document.getElementById('btnLogin').click()";
// Set a web view client for web view
        webView.setWebViewClient(new WebViewClient(){

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


        return rootView;

    }


}
