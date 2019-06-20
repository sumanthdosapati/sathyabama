package com.example.sumanth.sathyabama;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sumanth.sathyabama.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Quiz extends AppCompatActivity {

    Button b1,b2,b3,b4;
    TextView questiontxt,timertxt;
    int total=0;
    int correct=0;
    int wrong=0;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        b1=(Button)findViewById(R.id.opt1);
        b2=(Button)findViewById(R.id.opt2);
        b3=(Button)findViewById(R.id.opt3);
        b4=(Button)findViewById(R.id.opt4);

        questiontxt=(TextView)findViewById(R.id.questiontxt);
        timertxt=(TextView)findViewById(R.id.timertxt);

        updatequestion();
        reverseTimer(20,timertxt);

    }

    private void updatequestion()
    {
        total++;
        if(total>3)
        {
            //open result activity
            Intent i=new Intent(Quiz.this,Score.class);
            i.putExtra("total",String.valueOf(total));
            i.putExtra("correct",String.valueOf(correct));
            i.putExtra("incorrect",String.valueOf(wrong));
            startActivity(i);

        }
        else
        {
            reference=FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question=dataSnapshot.getValue(Question.class);

                    questiontxt.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b1.getText().toString().equals(question.getAnswer()))
                            {
                                b1.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.parseColor("#F57C00"));

                                        updatequestion();

                                    }
                                },1500);
                            }
                            else {
                                //if b1 answer is wrong, find correct ans and make it green
                                wrong++;
                                b1.setBackgroundColor(Color.RED);

                                if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b2.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b3.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b4.setBackgroundColor(Color.parseColor("#F57C00"));
                                        updatequestion();
                                    }
                                }, 1500);


                            }
                        }
                    });




                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b2.getText().toString().equals(question.getAnswer()))
                            {
                                b2.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b2.setBackgroundColor(Color.parseColor("#F57C00"));

                                        updatequestion();

                                    }
                                },1500);
                            }
                            else {
                                //if b1 answer is wrong, find correct ans and make it green
                                wrong++;
                                b2.setBackgroundColor(Color.RED);

                                if (b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b2.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b3.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b4.setBackgroundColor(Color.parseColor("#F57C00"));
                                        updatequestion();
                                    }
                                }, 1500);


                            }
                        }
                    });




                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b3.getText().toString().equals(question.getAnswer()))
                            {
                                b3.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b3.setBackgroundColor(Color.parseColor("#F57C00"));

                                        updatequestion();

                                    }
                                },1500);
                            }
                            else {
                                //if b3 answer is wrong, find correct ans and make it green
                                wrong++;
                                b3.setBackgroundColor(Color.RED);

                                if (b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b2.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b3.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b4.setBackgroundColor(Color.parseColor("#F57C00"));
                                        updatequestion();
                                    }
                                }, 1500);


                            }
                        }
                    });



                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b4.getText().toString().equals(question.getAnswer()))
                            {
                                b4.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b4.setBackgroundColor(Color.parseColor("#F57C00"));

                                        updatequestion();

                                    }
                                },1500);
                            }
                            else {
                                //if b4 answer is wrong, find correct ans and make it green
                                wrong++;
                                b4.setBackgroundColor(Color.RED);

                                if (b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b2.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b3.setBackgroundColor(Color.parseColor("#F57C00"));
                                        b4.setBackgroundColor(Color.parseColor("#F57C00"));
                                        updatequestion();
                                    }
                                }, 1500);


                            }
                        }
                    });




                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


    public void reverseTimer(int seconds,final TextView tv)
    {
        new CountDownTimer(seconds*1000 + 1000, 1000)
        {
            public void onTick(long millisUntilFinished){
                int seconds=(int)(millisUntilFinished/1000);
                int minutes=seconds/60;
                seconds=seconds%60;
                tv.setText(String.format("%02d",minutes)
                        + ":" + String.format("%02d",seconds));
            }
            public void onFinish(){
                tv.setText("Time Up");
                Intent myintent=new Intent(Quiz.this,Score.class);
                myintent.putExtra("total",String.valueOf(total));
                myintent.putExtra("correct",String.valueOf(correct));
                myintent.putExtra("incorrect",String.valueOf(wrong));
                startActivity(myintent);

            }
        }.start();

    }


}
