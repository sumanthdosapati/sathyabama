package com.example.sumanth.sathyabama;

import com.google.firebase.database.IgnoreExtraProperties;

public class User {

    public String name;
    public String email;
    public String registernum;
    public String dob;
    public String pass;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email,String registernum,String dob,String pass) {
        this.name = name;
        this.email = email;
        this.registernum=registernum;
        this.dob=dob;
        this.pass=pass;
    }

}
