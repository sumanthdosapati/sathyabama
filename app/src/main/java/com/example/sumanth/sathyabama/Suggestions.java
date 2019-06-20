package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bernaferrari.emojislider.EmojiSlider;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class Suggestions extends AppCompatActivity {

    EditText ed1;
    String to,subject;
    String message="";
    Button b1;
    EmojiSlider e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);


        final EmojiSlider slider = findViewById(R.id.slider);
        slider.setStartTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                Log.d("D", "setBeginTrackingListener");
                return Unit.INSTANCE;
            }
        });

        slider.setStopTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                Log.d("D", "setEndTrackingListener");
                return Unit.INSTANCE;
            }
        });

        ed1=(EditText)findViewById(R.id.reportext);

        b1=(Button) findViewById(R.id.suggestbutton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                to="sumanthdosapati3@gmail.com";
                subject="Your Suggestion for SATHYABAMA app";
                message=ed1.getText().toString();

                Intent n=new Intent(Intent.ACTION_SEND);

                n.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                n.putExtra(Intent.EXTRA_SUBJECT,subject);
                n.putExtra(Intent.EXTRA_TEXT,message);

                n.setType("message/rfc822");

                startActivity(Intent.createChooser(n,"Select Gmail:"));
                Toast.makeText(Suggestions.this, "Choose Gmail only", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
