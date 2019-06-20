package com.example.sumanth.sathyabama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.example.sumanth.sathyabama.R;

public class Detail extends AppCompatActivity {

    TextView nameDetailTextView,descriptionDetailTextView,dateDetailTextView,categoryDetailTextView,mailid;
    ImageView teacherDetailImageView;


    private void initializeWidgets(){
        nameDetailTextView= findViewById(R.id.namedetail);
        descriptionDetailTextView= findViewById(R.id.descriptiondetail);
        dateDetailTextView= findViewById(R.id.datedetail);
        categoryDetailTextView= findViewById(R.id.categorydetail);
        teacherDetailImageView=findViewById(R.id.imageviewid);
        mailid=findViewById(R.id.senderdetail);

    }

    private String getDateToday(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date();
        String today= dateFormat.format(date);
        return today;
    }
    private String getRandomCategory(){
        String[] categories={"Upload","Uploads"};
        Random random=new Random();
        int index=random.nextInt(categories.length-1);
        return categories[index];
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        initializeWidgets();

        //RECEIVE DATA FROM ITEMSACTIVITY VIA INTENT
        Intent i=this.getIntent();
        String name=i.getExtras().getString("NAME_KEY");
        String description=i.getExtras().getString("DESCRIPTION_KEY");
        String imageURL=i.getExtras().getString("IMAGE_KEY");
        String mail=i.getExtras().getString("SENDER_KEY");

        //SET RECEIVED DATA TO TEXTVIEWS AND IMAGEVIEWS
        nameDetailTextView.setText(name);
        descriptionDetailTextView.setText(description);
        mailid.setText("Posted by: "+mail);
        dateDetailTextView.setText("DATE: "+getDateToday());
        categoryDetailTextView.setText("CATEGORY: "+getRandomCategory());
        Picasso.get()
                .load(imageURL)
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(teacherDetailImageView);


    }
}
