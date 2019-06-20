package com.example.sumanth.sathyabama;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class SignupPage extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputname,inputregnum,inputdob,inputcnfpass;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputname =findViewById(R.id.name);
        inputregnum=findViewById(R.id.registernumb);
        inputdob=findViewById(R.id.dob);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        inputcnfpass=(EditText) findViewById(R.id.cnfpassword);



        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupPage.this, ResetPassword.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String name=inputname.getText().toString().trim();
                final String number=inputregnum.getText().toString().trim();
                final String dob=inputdob.getText().toString().trim();
                final String pass =inputcnfpass.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toasty.warning(getApplicationContext(),"Enter email address!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toasty.warning(getApplicationContext(),"Enter Password!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toasty.warning(getApplicationContext(),"Enter your name!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(number)) {
                    Toasty.warning(getApplicationContext(),"Enter your register number!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(dob)) {
                    Toasty.warning(getApplicationContext(),"Enter your date of birth in dd/mm/yyyy format as per college records ",Toast.LENGTH_LONG).show();
                    return;
                }
                if ((dob.charAt(2) != '/') && (dob.charAt(5) != '/')) {
                    Toast.makeText(getApplicationContext(), "date should be in dd/mm/yyyy format only ", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupPage.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toasty.warning(SignupPage.this,"Authentication failed  "+ task.getException(),Toast.LENGTH_SHORT).show();
                                } else {

                                    User user=new User(
                                            name,
                                            email,
                                            number,
                                            dob,
                                            pass
                                    );
                                    FirebaseDatabase.getInstance().getReference("Users/")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toasty.success(SignupPage.this,"User Registered Successfully",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(SignupPage.this,ProfilePic.class));
                                                finish();
                                            }
                                        }
                                    });
                                }
                            }
                        });

                }




        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);


    }


    }


