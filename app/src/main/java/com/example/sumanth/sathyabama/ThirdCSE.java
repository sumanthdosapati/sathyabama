package com.example.sumanth.sathyabama;

import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import es.dmoral.toasty.Toasty;

public class ThirdCSE extends AppCompatActivity {

    Button b1,b2,b3;
    DownloadManager downloadManager;
    // Progress Dialog
    private ProgressDialog pDialog;

    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;

    // File url to download
    private static String file_url ="http://www.sathyabama.ac.in/uploads/notes/note_1438938683.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_cse);

        // show progress bar button
        b2= (Button) findViewById(R.id.button2);

        b1=(Button)findViewById(R.id.button1);
        b3=(Button) findViewById(R.id.button3);

     /*   b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1438938683.pdf");
                DownloadManager.Request request=new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference=downloadManager.enqueue(request);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting new Async Task
                new DownloadFileFromURL().execute(file_url);
            }
        });  */

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1454926358.pdf"));
                startActivity(z);
            }
        });

    }

    //machine learning subject
    public void ml1(View v){
        Intent ma1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1518944917.pdf"));
        startActivity(ma1);
    }
    public void ml2(View v){
        Intent ma2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1518944988.pdf"));
        startActivity(ma2);
    }
    public void ml3(View v){
        Intent ma3=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1522474160.pdf"));
        startActivity(ma3);
    }
    public void ml4(View v){
        Intent ma4=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1522474222.pdf"));
        startActivity(ma4);
    }
    public void ml5(View v){
        Intent ma5=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1522474305.pdf"));
        startActivity(ma5);
    }

    //Network security
    public void ns1(View v){
        Intent ns1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1469517648.pdf"));
        startActivity(ns1);
    }
    public void ns2(View v){
        Intent ns2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1470207120.pdf"));
        startActivity(ns2);
    }
    public void ns3(View v){
        Intent ns3=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1476275470.pdf"));
        startActivity(ns3);
    }
    public void ns4(View v){
        Intent ns4=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1476276122.pdf"));
        startActivity(ns4);
    }
    public void ns5(View v){
        Intent ns5=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1476276170.pdf"));
        startActivity(ns5);
    }

    //data mining and warehousing
    public void dm1(View v){
        Intent dm1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1519095021.pdf"));
        startActivity(dm1);
    }
    public void dm2(View v){
        Intent dm2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1519201430.pdf"));
        startActivity(dm2);
    }
    public void dm3(View v){
        Intent dm3=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1522830485.pdf"));
        startActivity(dm3);
    }
    public void dm4(View v){
        Intent dm4=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1522830661.pdf"));
        startActivity(dm4);
    }
    public void dm5(View v){
        Intent dm5=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1525512798.pdf"));
        startActivity(dm5);
    }

    //cloud computing
    public void cc1(View v){
        Intent cc1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1519010990.pdf"));
        startActivity(cc1);
    }
    public void cc2(View v){
        Intent cc2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1519011018.pdf"));
        startActivity(cc2);
    }
    public void cc3(View v){
        Intent cc3=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1522473725.PDF"));
        startActivity(cc3);
    }
    public void cc4(View v){
        Intent cc4=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1522470346.pdf"));
        startActivity(cc4);
    }
    public void cc5(View v){
        Toasty.error(this, "Not Available... Please contact your staff :)", Toast.LENGTH_LONG, true).show();
    }

    //object oriented analysis
    public void oo1(View v){
        Intent oo1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1453714452.pdf"));
        startActivity(oo1);
    }
    public void oo2(View v){
        Intent oo2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1457762799.pdf"));
        startActivity(oo2);
    }
    public void oo3(View v){
        Intent oo3=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1457762858.pdf"));
        startActivity(oo3);
    }
    public void oo4(View v){
        Intent oo4=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1457762886.pdf"));
        startActivity(oo4);
    }
    public void oo5(View v){
        Intent oo5=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1460192506.pdf"));
        startActivity(oo5);
    }

    //python
    public void p1(View v){
        Intent p1=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1502861634.pdf"));
        startActivity(p1);
    }
    public void p2(View v){
        Intent p2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1502861660.pdf"));
        startActivity(p2);
    }
    public void p3(View v){
        Intent p3=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1503653253.pdf"));
        startActivity(p3);
    }
    public void p4(View v){
        Intent p4=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1503999671.pdf"));
        startActivity(p4);
    }
    public void p5(View v){
        Intent p5=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sathyabama.ac.in/uploads/notes/note_1503999693.PDF"));
        startActivity(p5);
    }


    /**
     * Showing Dialog
     * */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... file_url) {
            int count;
            try {
                URL url = new URL(file_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream
                OutputStream output = new FileOutputStream("downloadedfile.pdf");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);


        }

    }

}
