package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class SearchBox extends AppCompatActivity {

    TextView code;
    EditText search;
    Button searchbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_box);

        code=findViewById(R.id.codetext);
        search=findViewById(R.id.search);
        searchbtn=findViewById(R.id.searchbtn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String code= search.getText().toString();

                if (TextUtils.isEmpty(code)) {
                    Toasty.warning(getApplicationContext(),"Enter subject code!",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent=new Intent(SearchBox.this,Temporary_webview.class);
                intent.putExtra("codes",code);
                startActivity(intent);
                finish();

            }
        });

    }
}
