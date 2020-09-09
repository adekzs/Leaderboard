package com.adeks.tabapp.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class StudentIq implements Parcelable {
    private String name;
    private int score;
    private String country;
    private String badgeUrl;

    public StudentIq(String name, int skillIq, String country, String badgeUrl) {
        this.name = name;
        this.score = skillIq;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }


    protected StudentIq(Parcel in) {
        name = in.readString();
        score = in.readInt();
        country = in.readString();
        badgeUrl = in.readString();
    }

    public static final Creator<StudentIq> CREATOR = new Creator<StudentIq>() {
        @Override
        public StudentIq createFromParcel(Parcel in) {
            return new StudentIq(in);
        }

        @Override
        public StudentIq[] newArray(int size) {
            return new StudentIq[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
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
                ", score=" + score +
                ", country='" + country + '\'' +
                ", badgeUri=" + badgeUrl +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(score);
        dest.writeString(country);
        dest.writeString(badgeUrl);
    }
}
