package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PassorFail extends AppCompatActivity {

    TextView name,number;
    ImageButton b1,b2;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passor_fail);

        name=findViewById(R.id.on);
        number=findViewById(R.id.tw);
        b1=findViewById(R.id.ok);
        b2=findViewById(R.id.notok);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            String email = user.getEmail();
        }
        else {}

        mDatabase = FirebaseDatabase.getInstance().getReference("Users/");

        mDatabase.child(auth.getCurrentUser().getUid()).child("registernum").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String reg = dataSnapshot.getValue(String.class);
                number.setText(reg);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child(auth.getCurrentUser().getUid()).child("dob").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String dob = dataSnapshot.getValue(String.class);
                name.setText(dob);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data1=number.getText().toString();
                String data2=name.getText().toString();
                Intent intent=new Intent(PassorFail.this,Assesment2.class);
                intent.putExtra("abc",data1);
                intent.putExtra("def",data2);
                startActivity(intent);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data1=number.getText().toString();
                String data2=name.getText().toString();
                Intent intent=new Intent(PassorFail.this,Assesment2.class);
                intent.putExtra("abc",data1);
                intent.putExtra("def",data2);
                startActivity(intent);
                finish();
            }
        });

    }
}
