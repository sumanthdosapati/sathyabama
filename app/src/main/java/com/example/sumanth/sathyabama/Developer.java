package com.example.sumanth.sathyabama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ru.github.igla.ferriswheel.FerrisWheelView;
import yanzhikai.textpath.PathAnimatorListener;
import yanzhikai.textpath.SyncTextPathView;

public class Developer extends AppCompatActivity {

    SyncTextPathView s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        FerrisWheelView ferris=(FerrisWheelView) findViewById(R.id.ferrisWheelView);
        ferris.startAnimation();

        s= findViewById(R.id.sumanth);
        s.startAnimation(0,1);

    }

}

