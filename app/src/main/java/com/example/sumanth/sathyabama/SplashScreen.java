package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT =2000;
    MediaPlayer oursound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        MediaPlayer oursound=MediaPlayer.create(this,R.raw.notification_sms);
        oursound.start();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent n=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(n);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }

}
