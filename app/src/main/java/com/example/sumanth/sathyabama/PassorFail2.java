package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PassorFail2 extends AppCompatActivity {

    TextView name,number;
    ImageButton b1,b2;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passor_fail2);

        name=findViewById(R.id.th);
        number=findViewById(R.id.fo);
        b1=findViewById(R.id.ok2);
        b2=findViewById(R.id.notok2);

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
                Intent intent=new Intent(PassorFail2.this,Semester2.class);
                intent.putExtra("ghi",data1);
                intent.putExtra("jkl",data2);
                startActivity(intent);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data1=number.getText().toString();
                String data2=name.getText().toString();
                Intent intent=new Intent(PassorFail2.this,Semester2.class);
                intent.putExtra("ghi",data1);
                intent.putExtra("jkl",data2);
                startActivity(intent);
                finish();
            }
        });


    }
}
