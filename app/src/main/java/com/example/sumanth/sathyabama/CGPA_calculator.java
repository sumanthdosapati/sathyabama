package com.example.sumanth.sathyabama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.goodiebag.pinview.Pinview;

public class CGPA_calculator extends AppCompatActivity {

    Button b1;
    Pinview sub1,sub2,sub3,sub4,sub5,sub6;
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa_calculator);

        b1=findViewById(R.id.calculatebtn);
        res=findViewById(R.id.cgparesult);
        sub1=findViewById(R.id.sub1);
        sub2=findViewById(R.id.sub2);
        sub3=findViewById(R.id.sub3);
        sub4=findViewById(R.id.sub4);
        sub5=findViewById(R.id.sub5);
        sub6=findViewById(R.id.sub6);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
