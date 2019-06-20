package com.example.sumanth.sathyabama;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.example.sumanth.sathyabama.R;
import com.squareup.picasso.Picasso;


import java.util.Objects;
public class Register extends AppCompatActivity {

    Button login, registerbtn;
    EditText email, password, registernumber, name;
    private ImageButton avatar;
    public DatabaseReference mDatabaseRef;
    public FirebaseDatabase mDataBase;
    private StorageReference mFirebaseStorage;

    private FirebaseAuth mAuth;

    String usern;

    ProgressDialog mProgressDialog;
    private Uri resultUri = null;
    private final static int gallerycode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mProgressDialog = new ProgressDialog(this);
//        user = mAuth.getCurrentUser();
        login = findViewById(R.id.login);
        registerbtn = findViewById(R.id.registerbtn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registernumber = findViewById(R.id.registernumber);
        name = findViewById(R.id.name);
        avatar=findViewById(R.id.avatarpic);
        mFirebaseStorage=FirebaseStorage.getInstance().getReference().child("UserProfilePics");

        mDataBase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDataBase.getReference().child("Users");
        mAuth = FirebaseAuth.getInstance();





        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent();
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery,gallerycode);


            }
        });


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = name.getText().toString().trim();
                final String em = email.getText().toString().trim();
                final String reg = registernumber.getText().toString().trim();
                final String pwd = password.getText().toString().trim();
                if (!TextUtils.isEmpty(username) &&
                        !TextUtils.isEmpty(em) &&
                        !TextUtils.isEmpty(reg) &&
                        !TextUtils.isEmpty(pwd)&& resultUri!=null){
                    createNewAccount();
                    finish();

                }else
                    Toast.makeText(Register.this,"Invalid Details/Add dp",Toast.LENGTH_SHORT).show();}
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login= new Intent(Register.this,MainActivity.class);
                startActivity(login);
                finish();

            }
        });
    }


    private void createNewAccount() {
        final String username = name.getText().toString().trim();
        final String em = email.getText().toString().trim();
        final String reg = registernumber.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        try{
            if (!TextUtils.isEmpty(username) &&
                    !TextUtils.isEmpty(em) &&
                    !TextUtils.isEmpty(reg) &&
                    !TextUtils.isEmpty(pwd)) {
                mProgressDialog.setMessage("Creating account..");
                mProgressDialog.show();

                mAuth.createUserWithEmailAndPassword(em, pwd)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if (authResult != null) {


                                    StorageReference imagepath = mFirebaseStorage.child("UserProfilePics")
                                            .child(resultUri.getLastPathSegment());

                                    imagepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Task<Uri> downloadurl = taskSnapshot.getMetadata().getReference().getDownloadUrl();

                                            String userid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                                            DatabaseReference currentuserdb = mDatabaseRef.child(userid);
                                            currentuserdb.child("name").setValue(username);
                                            currentuserdb.child("email").setValue(em);
                                            currentuserdb.child("image").setValue(downloadurl.toString());
                                            currentuserdb.child("register").setValue(reg);
                                            currentuserdb.child("verified").setValue("false");
                                            currentuserdb.child("about").setValue("");
                                            mProgressDialog.dismiss();





                                            Intent intent = new Intent(Register.this, MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);

                                            //finish();


                                        }


                                    });

                                }}




                        });
            }


        }catch(Exception e){} }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == gallerycode && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            resultUri = data.getData();

            Picasso.get().load(resultUri).into(avatar);
        }
    }


    @Override
    protected void onDestroy() {
        mProgressDialog.dismiss();
        super.onDestroy();


    }
}
