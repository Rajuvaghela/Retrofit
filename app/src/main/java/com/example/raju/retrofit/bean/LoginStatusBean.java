package com.example.raju.retrofit.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LoginStatusBean implements Parcelable {
    @SerializedName("Status")
    public String Status;

    @SerializedName("Status_Message")
    public String Status_Message;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Status);
        dest.writeString(this.Status_Message);
    }

    public LoginStatusBean() {
    }

    protected LoginStatusBean(Parcel in) {
        this.Status = in.readString();
        this.Status_Message = in.readString();
    }

    public static final Parcelable.Creator<LoginStatusBean> CREATOR = new Parcelable.Creator<LoginStatusBean>() {
        @Override
        public LoginStatusBean createFromParcel(Parcel source) {
            return new LoginStatusBean(source);
        }

        @Override
        public LoginStatusBean[] newArray(int size) {
            return new LoginStatusBean[size];
        }
    };
}
