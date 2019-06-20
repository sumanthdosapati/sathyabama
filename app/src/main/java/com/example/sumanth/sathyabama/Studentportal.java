package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Studentportal extends AppCompatActivity implements View.OnClickListener {

    ImageView fif,six,sev,eig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentportal);

        fif=findViewById(R.id.fifteen);
        six=findViewById(R.id.sixteen);
        sev=findViewById(R.id.seventeen);
        eig=findViewById(R.id.eighteen);

        fif.setOnClickListener(this);
        six.setOnClickListener(this);
        sev.setOnClickListener(this);
        eig.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fifteen:
                Intent fif=new Intent(getApplicationContext(),Sp15.class);
                startActivity(fif);
                break;
            case R.id.sixteen:
                Intent six=new Intent(getApplicationContext(),Sp16.class);
                startActivity(six);
                break;
            case R.id.seventeen:
                Intent sev=new Intent(getApplicationContext(),Sp17.class);
                startActivity(sev);
                break;
            case R.id.eighteen:
                Intent eig=new Intent(getApplicationContext(),Sp18.class);
                startActivity(eig);
                break;
        }

    }
}
