package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    TextView tot,cor,wro;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tot=findViewById(R.id.textView2);
        cor=findViewById(R.id.textView4);
        wro=findViewById(R.id.textView6);

        Intent i=getIntent();

        String questions=i.getStringExtra("total");
        String correct=i.getStringExtra("correct");
        String incorrect=i.getStringExtra("incorrect");

        tot.setText(questions);
        cor.setText(correct);
        wro.setText(incorrect);


        ok=findViewById(R.id.okbtn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ok=new Intent(getApplicationContext(),List.class);
                startActivity(ok);
            }
        });

    }
}
