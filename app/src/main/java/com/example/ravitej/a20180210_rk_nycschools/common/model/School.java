package com.example.ravitej.a20180210_rk_nycschools.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class School implements Parcelable{

    @Expose
    @SerializedName("dbn")
    private String dbn;

    @Expose
    @SerializedName("num_of_sat_test_takers")
    private String noOfSatTestTakers;

    @Expose
    @SerializedName("sat_critical_reading_avg_score")
    private String readingScore;

    @Expose
    @SerializedName("sat_math_avg_score")
    private String mathScore;

    @Expose
    @SerializedName("sat_writing_avg_score")
    private String writingScore;

    @Expose
    @SerializedName("school_name")
    private String schoolName;

    public School(Parcel in) {
        dbn = in.readString();
        noOfSatTestTakers = in.readString();
        readingScore = in.readString();
        mathScore = in.readString();
        writingScore = in.readString();
        schoolName = in.readString();
    }

    public static final Creator<School> CREATOR = new Creator<School>() {
        @Override
        public School createFromParcel(Parcel in) {
            return new School(in);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };

    public School() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dbn);
        parcel.writeString(noOfSatTestTakers);
        parcel.writeString(readingScore);
        parcel.writeString(mathScore);
        parcel.writeString(writingScore);
        parcel.writeString(schoolName);
    }

    public String getDbn() {
        return dbn;
    }

    public String getNoOfSatTestTakers() {
        return noOfSatTestTakers;
    }

    public String getReadingScore() {
        return readingScore;
    }

    public String getMathScore() {
        return mathScore;
    }

    public String getWritingScore() {
        return writingScore;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public void setNoOfSatTestTakers(String noOfSatTestTakers) {
        this.noOfSatTestTakers = noOfSatTestTakers;
    }

    @Override
    public String toString() {
        return schoolName.toString();
    }

    public void setReadingScore(String readingScore) {
        this.readingScore = readingScore;
    }

    public void setMathScore(String mathScore) {
        this.mathScore = mathScore;
    }

    public void setWritingScore(String writingScore) {
        this.writingScore = writingScore;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
