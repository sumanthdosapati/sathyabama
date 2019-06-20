package com.example.sumanth.sathyabama;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karan.churi.PermissionManager.PermissionManager;


import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    Button b1,fpb,regb;
    TextView staff;
    private FirebaseAuth auth;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ProgressBar progressBar;
    PermissionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, List.class));
            finish();
        }
        else
        {
            setContentView(R.layout.activity_main);
            manager=new PermissionManager() {};
            manager.checkAndRequestPermissions(this);
        }

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



        // set the view now
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e1=(EditText) findViewById(R.id.useredit);
        e2=(EditText) findViewById(R.id.passedit);
        staff=(TextView) findViewById(R.id.staff);
        fpb=(Button) findViewById(R.id.forpass);
        regb=(Button) findViewById(R.id.register);
        b1=(Button)findViewById(R.id.clibutton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        regb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rg=new Intent(getApplicationContext(),SignupPage.class);
                startActivity(rg);
            }
        });
        fpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fp=new Intent(getApplicationContext(),ResetPassword.class);
                startActivity(fp);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                final String password = e2.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toasty.warning(getApplicationContext(),"Enter email address!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toasty.warning(getApplicationContext(),"Enter Password!",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        e2.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toasty.warning(MainActivity.this,"Login failed ! Check your email and password or Signup",Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(MainActivity.this, List.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

////////////////////////////////////////////////////////////////
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }

            }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        manager.checkResult(requestCode,permissions,grantResults);

        ArrayList<String> denied_permissions = manager.getStatus().get(0).denied;

        if(denied_permissions.isEmpty())
        {
            Toasty.success(getApplicationContext(),"Permissions Granted",Toast.LENGTH_SHORT).show();
        }
    }


}
