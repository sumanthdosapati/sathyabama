package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import es.dmoral.toasty.Toasty;

public class CourseMaterials extends AppCompatActivity {

    Button b1;
    RadioGroup yr,dep;
    RadioButton on,tw,th,fo,cs,ec,i,me,ee,au,ci;
    private boolean isChecking = true;
    private int mCheckedId = R.id.one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_materials);


        b1=(Button)findViewById(R.id.submit);

        yr=(RadioGroup)findViewById(R.id.year);
        dep=(RadioGroup)findViewById(R.id.dept);
        yr.clearCheck();
        dep.clearCheck();

        on=(RadioButton)findViewById(R.id.one);
        tw=(RadioButton)findViewById(R.id.two);
        th=(RadioButton)findViewById(R.id.three);
        fo=(RadioButton)findViewById(R.id.four);

        cs=(RadioButton)findViewById(R.id.cse);
        ec=(RadioButton)findViewById(R.id.ece);
        me=(RadioButton)findViewById(R.id.mech);
        i=(RadioButton)findViewById(R.id.it);
        ee=(RadioButton)findViewById(R.id.eee);
        au=(RadioButton)findViewById(R.id.auto);
        ci=(RadioButton)findViewById(R.id.civil);



b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (th.isChecked() && cs.isChecked()) {
            Intent thcs = new Intent(getApplicationContext(), ThirdCSE.class);
            startActivity(thcs);
        }
        else if((!on.isChecked()) && (!tw.isChecked()) && (!th.isChecked()) && (!fo.isChecked())){
            Toasty.warning(getApplicationContext(),"Please select the year !", Toast.LENGTH_SHORT).show();
        }
        else if((!cs.isChecked()) && (!ec.isChecked()) && (!me.isChecked()) && (!i.isChecked()) && (!ee.isChecked()) && (!au.isChecked()) && (!ci.isChecked())){
            Toasty.warning(getApplicationContext(),"Please select the Department !", Toast.LENGTH_SHORT).show();
        }
        else  {
            Intent temp = new Intent(getApplicationContext(), SearchBox.class);
            startActivity(temp);

        }
    }
});

    }
}
