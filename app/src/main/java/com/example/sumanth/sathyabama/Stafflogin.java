package com.example.sumanth.sathyabama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Stafflogin extends AppCompatActivity {

    EditText stid,stpw;
    Button stlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stafflogin);

        stid=(EditText)findViewById(R.id.stid);
        stpw=(EditText)findViewById(R.id.stpw);
        stlogin=(Button)findViewById(R.id.stlogin);

        stlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stid.getText().toString()==""){
                    Toasty.error(Stafflogin.this,"Please Enter the Staff ID",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(stpw.getText().toString()==""){
                    Toasty.error(Stafflogin.this,"Password cannot be empty !",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toasty.error(Stafflogin.this,"Sorry! The details entered do not match any staff in the records.",Toast.LENGTH_LONG).show();
            }
        });

    }
}