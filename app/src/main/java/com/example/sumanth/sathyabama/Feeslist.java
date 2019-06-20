package com.example.sumanth.sathyabama;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static com.example.sumanth.sathyabama.NetworkStateChangeReciever.IS_NETWORK_AVAILABLE;
import static com.example.sumanth.sathyabama.NetworkStateChangeReciever.IS_NETWORK_AVAILABLE;


public class Feeslist extends AppCompatActivity {

    ImageView im1,im2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeslist);

        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReciever.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
                String networkStatus = isNetworkAvailable ? "connected" : "disconnected";

                Snackbar.make(findViewById(R.id.fee_list), "Network Status: " + networkStatus, Snackbar.LENGTH_LONG).show();
            }
        }, intentFilter);

        im1=(ImageView) findViewById(R.id.collegebut);
        im2=(ImageView) findViewById(R.id.arrearbut);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent co= new Intent(getApplicationContext(),Collegefees.class);
                startActivity(co);

            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent af= new Intent(getApplicationContext(),Arrearfees.class);
                startActivity(af);

            }
        });



    }
}
