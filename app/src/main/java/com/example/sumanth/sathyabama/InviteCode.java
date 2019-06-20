package com.example.sumanth.sathyabama;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;


import es.dmoral.toasty.Toasty;

public class InviteCode extends AppCompatActivity {

    String name,email,password,date,issharing,code;
    Uri imageUri;
    TextView t1;
    Button b1;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    StorageReference storageReference;
    StorageTask uploadTask;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);

        t1=findViewById(R.id.buscode);
        b1=(Button)findViewById(R.id.codebutton);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference().child("TrackingData");
        storageReference=FirebaseStorage.getInstance().getReference().child("User_images");
        progressDialog= new ProgressDialog(this);

        Intent myIntent = getIntent();

        if(myIntent!=null)
        {
            code=myIntent.getStringExtra("code");
            date=myIntent.getStringExtra("date");
            issharing=myIntent.getStringExtra("isSharing");
            imageUri=myIntent.getParcelableExtra("imageUri");
        }

        t1.setText(code);

    }

    public void registerCode(View v)
    {
        progressDialog.setMessage("Please wait your data is being added to our servers");
        progressDialog.show();

        CreateCode createCode =new CreateCode(code,"false","na","na","na",user.getUid());
        user=auth.getCurrentUser();
        userId=user.getUid();
        reference.child(userId).setValue(createCode)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {

                            final StorageReference sr =storageReference.child(user.getUid() + ".jpg");
                            uploadTask=sr.putFile(imageUri);


                            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        throw task.getException();
                                    }

                                    // Continue with the task to get the download URL
                                    return sr.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        String downloadUri = task.getResult().toString();
                                        reference.child(user.getUid()).child("imageUrl").setValue(downloadUri);

                                        Toasty.success(getApplicationContext(),"Your data has been registered successfully",Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                        auth.signOut();
                                        Intent ok = new Intent(InviteCode.this,MainActivity.class);
                                        startActivity(ok);


                                    } else {
                                        // Handle failures
                                        // ...
                                    }
                                }
                            });




                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toasty.error(getApplicationContext(),"Error adding tracking code to server !",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }




}
