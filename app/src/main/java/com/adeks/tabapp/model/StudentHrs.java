package com.adeks.tabapp.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.net.URL;

public class StudentHrs implements Parcelable {

    private String name;
    private int hours;
    private String country;
    private String badgeUrl;

    public StudentHrs(String name, int hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }


    protected StudentHrs(Parcel in) {
        name = in.readString();
        hours = in.readInt();
        country = in.readString();
        badgeUrl = in.readString();
    }

    public static final Creator<StudentHrs> CREATOR = new Creator<StudentHrs>() {
        @Override
        public StudentHrs createFromParcel(Parcel in) {
            return new StudentHrs(in);
        }

        @Override
        public StudentHrs[] newArray(int size) {
            return new StudentHrs[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }

    public String getImageUri() {
        return badgeUrl;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", hours=" + hours +
                ", country='" + country + '\'' +
                ", badgeUrl=" + badgeUrl +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(hours);
        dest.writeString(country);
        dest.writeString(badgeUrl);
    }
}
