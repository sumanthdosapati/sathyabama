package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Resultslist extends AppCompatActivity {

    ImageView a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultslist);

        a=findViewById(R.id.assesbut);
        b=findViewById(R.id.semesbut);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent as=new Intent(getApplicationContext(),PassorFail.class);
                startActivity(as);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent se=new Intent(getApplicationContext(),PassorFail2.class);
                startActivity(se);
            }
        });

    }
}
