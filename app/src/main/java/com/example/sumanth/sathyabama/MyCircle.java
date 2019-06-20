package com.example.sumanth.sathyabama;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyCircle extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference,usersReference;
    CreateCode createCode;
    ArrayList<CreateCode> namelist;
    String circlememberid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle);

        recyclerView = findViewById(R.id.recyclerview);
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        namelist = new ArrayList<>();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        usersReference = FirebaseDatabase.getInstance().getReference().child("TrackingData");
        reference =FirebaseDatabase.getInstance().getReference().child("TrackingData").child(user.getUid()).child("circlemembers");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namelist.clear();

                if(dataSnapshot.exists())
                {
                    for (DataSnapshot dss : dataSnapshot.getChildren())
                    {
                       circlememberid = dss.child("circlememberid").getValue(String.class);
                       usersReference.child(circlememberid)
                               .addListenerForSingleValueEvent(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                       createCode = dataSnapshot.getValue(CreateCode.class);
                                       namelist.add(createCode);
                                       adapter.notifyDataSetChanged();
                                   }

                                   @Override
                                   public void onCancelled(@NonNull DatabaseError databaseError) {
                                       Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                   }
                               });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new MembersAdapter(namelist,getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
