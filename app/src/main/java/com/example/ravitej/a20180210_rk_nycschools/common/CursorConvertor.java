package com.example.ravitej.a20180210_rk_nycschools.common;

import android.database.Cursor;

import com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract;
import com.example.ravitej.a20180210_rk_nycschools.common.model.School;
import com.example.ravitej.a20180210_rk_nycschools.common.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class CursorConvertor {

    //Convert the data fetched from data base as cursor to Java model object..
    public static List<School> convertSchoolsFromCursor(@NonNull Cursor cursor){
        List<School> schoolList = new ArrayList<>();

        if (cursor.isClosed()){
            return schoolList;
        }

        cursor.moveToPosition(-1);

        while(cursor.moveToNext()){
            School school = new School();
            school.setSchoolName(cursor.getString(cursor.getColumnIndex(NYCSchoolsContract.SchoolEntry.COLUMN_SCHOOL_NAME)));
            school.setMathScore(cursor.getString(cursor.getColumnIndex(NYCSchoolsContract.SchoolEntry.COLUMN_MATH_SCORE)));
            school.setReadingScore(cursor.getString(cursor.getColumnIndex(NYCSchoolsContract.SchoolEntry.COLUMN_READING_SCORE)));
            school.setWritingScore(cursor.getString(cursor.getColumnIndex(NYCSchoolsContract.SchoolEntry.COLUMN_WRITING_SCORE)));
            school.setNoOfSatTestTakers(cursor.getString(cursor.getColumnIndex(NYCSchoolsContract.SchoolEntry.COLUMN_NO_SAT_TAKES)));
            schoolList.add(school);
        }

        return schoolList;
    }
}
