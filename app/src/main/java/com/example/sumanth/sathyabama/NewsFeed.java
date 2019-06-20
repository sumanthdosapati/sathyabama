package com.example.sumanth.sathyabama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.sumanth.sathyabama.R;

public class NewsFeed extends AppCompatActivity {

    private Button openTeachersActivityBtn,openUploadActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        openTeachersActivityBtn=(Button) findViewById(R.id.showbtn);
        openUploadActivityBtn=(Button) findViewById(R.id.postbtn);

        openTeachersActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewsFeed.this, Items.class);
                startActivity(i);
            }
        });
        openUploadActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewsFeed.this, Upload.class);
                startActivity(i);
            }
        });


    }
}
