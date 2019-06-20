package com.example.sumanth.sathyabama;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Testing extends AppCompatActivity {

    TextView first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        first=findViewById(R.id.firsttext);
        Button btn=findViewById(R.id.btn);



                String firs = first.getText().toString();
                char[] myNameChars = firs.toCharArray();
                myNameChars[4] = 'x';
                firs = String.valueOf(myNameChars);
                first.setText(String.valueOf(firs));


    }
}
