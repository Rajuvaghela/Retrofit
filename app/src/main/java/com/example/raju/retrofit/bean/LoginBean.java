package com.example.raju.retrofit.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.stream.Stream;

public class LoginBean {

    @SerializedName("Pepole_Login")
    public ArrayList<LoginStatusBean> Pepole_Login;

}
