package com.example.sumanth.sathyabama;

import android.os.Environment;

public class CheckForSDcard {

    //Check If SD Card is present or not method
    public boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
