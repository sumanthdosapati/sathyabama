package com.example.sumanth.sathyabama;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

import static com.example.sumanth.sathyabama.NetworkStateChangeReciever.IS_NETWORK_AVAILABLE;


public class List extends AppCompatActivity {


    ImageView par, fee,res,cou,nfeed,spor;
    Button sout,pro;
    TextView topname,mail2;
    private ProgressBar progressBar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    String newslink ="http://67.225.162.5/~sathyamobile/api/index_latest_news.php";
    String sportslink = "http://67.225.162.5/~sathyamobile/api/index_sports_news.php";
    String eventslink = "http://67.225.162.5/~sathyamobile/api/index_latest_events.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orangelight)));

        if(!isConnected(List.this)) buildDialog(List.this).show();
        else {}

        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReciever.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
                String networkStatus = isNetworkAvailable ? "connected" : "disconnected";

                Snackbar.make(findViewById(R.id.dlout), "Network Status: " + networkStatus, Snackbar.LENGTH_LONG).show();
            }
        }, intentFilter);

        topname=(TextView) findViewById(R.id.usermail);
        mail2=findViewById(R.id.hedermail);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3= (FloatingActionButton)findViewById(R.id.material_design_floating_action_menu_item3);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shareTextUrl();

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent dv= new Intent(getApplicationContext(),Developer.class);
                startActivity(dv);
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ch=new Intent(getApplicationContext(),Chat.class);
                startActivity(ch);
            }
        });

        drawerLayout=(DrawerLayout) findViewById(R.id.dlout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView=(NavigationView) findViewById(R.id.nvview);
        View headerView = navigationView.getHeaderView(0);
        TextView navmail = (TextView) headerView.findViewById(R.id.hedermail);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id) {
                    case R.id.action_qu:
                        Intent qu = new Intent(getApplicationContext(), Quiz.class);
                        startActivity(qu);
                        break;

                    case R.id.action_cg:
                        Intent cg = new Intent(getApplicationContext(), CGPA_calculator.class);
                        startActivity(cg);
                        break;

                    case R.id.action_ga:
                        Intent ga = new Intent(getApplicationContext(), Galery.class);
                        startActivity(ga);
                        break;

                    case R.id.action_suggest:
                        Intent su = new Intent(getApplicationContext(), Suggestions.class);
                        startActivity(su);
                        break;

                    case R.id.action_map:
                        Intent ma = new Intent(getApplicationContext(), CampusMap.class);
                        startActivity(ma);
                        break;

                    case R.id.action_news:
                        Intent ne = new Intent(getApplicationContext(),CommonWebview.class);
                        ne.putExtra("newslink",newslink);
                        startActivity(ne);
                        break;
                    case R.id.action_sports:
                        Intent sp = new Intent(getApplicationContext(),CommonWebview.class);
                        sp.putExtra("sportslink",sportslink);
                        startActivity(sp);
                        break;
                    case R.id.action_events:
                        Intent ev = new Intent(getApplicationContext(),CommonWebview.class);
                        ev.putExtra("eventslink",eventslink);
                        startActivity(ev);
                        break;

                    case R.id.action_track:
                        Intent tr =new Intent(getApplicationContext(), SathyabamaMapLocation.class);
                        startActivity(tr);
                        break;

                    case R.id.action_share:
                        shareTextUrl();
                        break;

                    case R.id.action_rate:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=com.example.sumanth.sathyabama")));
                        } catch (android.content.ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=com.example.sumanth.sathyabama")));
                        }
                        break;

                    case R.id.action_developer:
                        Intent dv= new Intent(getApplicationContext(),DeveloperProfile.class);
                        startActivity(dv);
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                            return true;

            }
        });


        //get firebase auth instance
        auth = FirebaseAuth.getInstance();


        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            String email = user.getEmail();
            navmail.setText(email);
            }
        else {}

        mDatabase = FirebaseDatabase.getInstance().getReference("Users/");

        mDatabase.child(auth.getCurrentUser().getUid()).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                topname.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(List.this, MainActivity.class));
                    finish();
                }

                else {}
            }
        };


        par = (ImageView) findViewById(R.id.parimage);
        fee = (ImageView) findViewById(R.id.feeimage);
        res =(ImageView) findViewById(R.id.resimage);
        cou =(ImageView) findViewById(R.id.cmimage);
        nfeed =(ImageView) findViewById(R.id.nfimage);
        spor=(ImageView) findViewById(R.id.stportalimage);
        sout =(Button) findViewById(R.id.sign_out);
        pro=(Button) findViewById(R.id.userprofile);



        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        par.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pa = new Intent(getApplicationContext(), Parentlogin.class);
                startActivity(pa);

            }
        });

        fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fe2 = new Intent(getApplicationContext(), Feeslist.class);
                startActivity(fe2);

            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent re2 =new Intent(getApplicationContext(),Resultslist.class);
                startActivity(re2);
            }
        });

        cou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cm2 = new Intent(getApplicationContext(), CourseMaterials.class);
                startActivity(cm2);

            }
        });

        nfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nf2 = new Intent(getApplicationContext(),NewsFeed.class);
                startActivity(nf2);

            }
        });

        spor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sp2 =new Intent(getApplicationContext(),Studentportal.class);
                startActivity(sp2);
            }
        });

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pr2 = new Intent(getApplicationContext(), UserProfile.class);
                startActivity(pr2);

            }
        });


        sout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }


    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }

    }


    // Method to share either text or URL.
    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Sathyabama Android App");
        share.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=com.example.sumanth.sathyabama");

        startActivity(Intent.createChooser(share, "Share Sathyabama App"));

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(List.this);
        builder.setTitle("Exit");
        builder.setIcon(R.mipmap.exiticon);
        builder.setMessage("Do you want to Exit")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isConnected(Context context)
    {ConnectivityManager cm=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo=cm.getActiveNetworkInfo();

        if(netinfo!=null && netinfo.isConnectedOrConnecting()){
            android.net.NetworkInfo wifi=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile!=null && mobile.isConnectedOrConnecting()) || (wifi!=null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        }
        else return false;
    }

    public AlertDialog.Builder buildDialog(Context c){
        AlertDialog.Builder builder = new AlertDialog.Builder(List.this);
        builder.setTitle("No Internet Connection");
        builder.setIcon(R.mipmap.exiticon);
        builder.setMessage("You can't live without food.. as so i can't without INTERNET");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        return builder;


    }

    }

