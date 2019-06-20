package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UserProfile extends AppCompatActivity {

    TextView name,number,mail,dobt;
    ImageView i1;
    String current_user_imageurl;
    private FirebaseAuth auth;
    FirebaseUser user;
    private DatabaseReference mDatabase,mDatabase2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name=findViewById(R.id.userproname);
        number=findViewById(R.id.userproregno);
        mail=findViewById(R.id.userpromail);
        dobt=findViewById(R.id.userprodob);
        i1=findViewById(R.id.userproimage);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            String email = user.getEmail();
            mail.setText(email);
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

        mDatabase.child(auth.getCurrentUser().getUid()).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nam = dataSnapshot.getValue(String.class);
                name.setText(nam);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child(auth.getCurrentUser().getUid()).child("dob").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String dob = dataSnapshot.getValue(String.class);
                dobt.setText(dob);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("TrackingData");
        mDatabase2.child(auth.getCurrentUser().getUid()).child("imageUrl").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                current_user_imageurl=dataSnapshot.getValue(String.class);
                Picasso.get().load(current_user_imageurl).into(i1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
