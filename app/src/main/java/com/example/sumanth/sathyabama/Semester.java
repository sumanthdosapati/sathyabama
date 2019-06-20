package com.example.sumanth.sathyabama;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class Semester extends Fragment {

    WebView webView;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.semester, container, false);

        webView=(WebView) rootView.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.sathyabama.ac.in/result.php");

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference("Users/");

        mDatabase.child(auth.getCurrentUser().getUid()).child("dob").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String dob = dataSnapshot.getValue(String.class);
                final String js = "javascript:document.getElementById('stu_dob').value='"+dob+"';" +
                        "document.getElementById('btnLogin').click()";
                webView.loadUrl("javascript: (function(){document.getElementById('stu_dob').value ='"+dob+"';})();");
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child(auth.getCurrentUser().getUid()).child("registernum").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String reg = dataSnapshot.getValue(String.class);
                final String js = "javascript:document.getElementById('Regnum').value='"+reg+"';";
                webView.loadUrl("javascript: (function(){document.getElementById('Regnum').value ='"+reg+"';})();");
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return rootView;
    }


}