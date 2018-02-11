package com.example.ravitej.a20180210_rk_nycschools.common.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.COLUMN_MATH_SCORE;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.COLUMN_NO_SAT_TAKES;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.COLUMN_READING_SCORE;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.COLUMN_SCHOOL_NAME;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.COLUMN_WRITING_SCORE;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.TABLE_NAME;

public class NYCSchoolsDBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "nycschools.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public NYCSchoolsDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CRAETE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " ("
                +   COLUMN_SCHOOL_NAME + " TEXT, "
                +   COLUMN_MATH_SCORE + " TEXT, "
                +   COLUMN_READING_SCORE + " TEXT, "
                +   COLUMN_WRITING_SCORE + " TEXT, "
                +   COLUMN_NO_SAT_TAKES + " TEXT ) ";

        sqLiteDatabase.execSQL(CRAETE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Drop older table if exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

        //create table again
        onCreate(sqLiteDatabase);
    }
}
