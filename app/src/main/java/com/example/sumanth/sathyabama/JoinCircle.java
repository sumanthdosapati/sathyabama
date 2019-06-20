package com.example.sumanth.sathyabama;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class JoinCircle extends AppCompatActivity {

    Button b1;
    Pinview pinview;
    DatabaseReference reference,currentreference, circlereference;
    FirebaseUser user;
    FirebaseAuth auth;
    String current_user_id, join_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_circle);

        pinview=findViewById(R.id.pinview);
        b1=findViewById(R.id.trackbut);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        reference=FirebaseDatabase.getInstance().getReference().child("TrackingData");
        currentreference=FirebaseDatabase.getInstance().getReference().child("TrackingData").child(user.getUid());
        current_user_id=user.getUid();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. to check if entered code exists and find which user code is that
                //2. if code is present, create a node(circlemembers)

                Query query= reference.orderByChild("code").equalTo(pinview.getValue());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            CreateCode createCode = null;
                            for(DataSnapshot childDss : dataSnapshot.getChildren())
                            {
                                createCode = childDss.getValue(CreateCode.class);
                                join_user_id = createCode.userid;


                                circlereference = FirebaseDatabase.getInstance().getReference().child("TrackingData")
                                        .child(join_user_id).child("CircleMembers");

                                CircleJoin circleJoin = new CircleJoin(current_user_id);
                                CircleJoin circleJoin1 = new CircleJoin(join_user_id);

                                circlereference.child(user.getUid()).setValue(circleJoin)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    Toasty.success(getApplicationContext(),"User joined the circle successfully",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                            }
                        }
                        else
                        {
                            Toasty.error(getApplicationContext(),"Invalid Code",Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });




    }
}
