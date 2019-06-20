package com.example.sumanth.sathyabama;

public class CreateCode
{
    public CreateCode()
    {}

    public String code;

    public CreateCode(String code, String isSharing, String lat, String lng, String imageUrl, String userid) {
        this.code = code;
        this.isSharing = isSharing;
        this.lat = lat;
        this.lng = lng;
        this.imageUrl = imageUrl;
        this.userid = userid;
    }

    public String isSharing;
    public String lat;
    public String lng;
    public String imageUrl;
    public String userid;


}
