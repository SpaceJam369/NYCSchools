package com.example.ravitej.a20180210_rk_nycschools.common;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract;
import com.example.ravitej.a20180210_rk_nycschools.common.model.School;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.COLUMN_MATH_SCORE;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.COLUMN_NO_SAT_TAKES;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.COLUMN_READING_SCORE;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.COLUMN_SCHOOL_NAME;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.COLUMN_WRITING_SCORE;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.CONTENT_URI;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.TABLE_NAME;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsProvider.METHOD_VACCUM;


//Intent Service is used to insert data into database on fetching from server..
public class DatabaseIntentService extends IntentService {

    public DatabaseIntentService(){super("DatabaseIntentService");}

    private static final String INSERT_SCHOOLS = "insert schools";
    private static final String SCHOOLS_LIST = "schools_list";
    private static final String DELETE_SCHOOLS = "delete_schools";
    private static final String LOG_TAG = DatabaseIntentService.class.getSimpleName();

    public DatabaseIntentService(String name) {
        super(name);
    }

    public static void insertSchools(Context context, List<School> schoolList){

        //Insert items at a time to prevent TransactionTooLargeExceptions
        //FIXME: Why this is inserting duplicate data when partition with 50 items..
        List<List<School>> partitionSchoolLists = Lists.partition(schoolList, 1);

        for (List<School> list : partitionSchoolLists){
            Intent intent = new Intent(context, DatabaseIntentService.class);
            intent.setAction(INSERT_SCHOOLS);
            intent.setData(NYCSchoolsContract.SchoolEntry.CONTENT_URI);
            intent.putParcelableArrayListExtra(SCHOOLS_LIST, new ArrayList<>(list));
            context.startService(intent);
        }
    }

    //before inserting data into database .. delete the existing data as we are not using any logout mechanism ..
    //delete it first and then insert the data...
    public static void deleteSchools(Context context){
        Intent intent = new Intent(context, DatabaseIntentService.class);
        intent.setAction(DELETE_SCHOOLS);
        intent.setData(NYCSchoolsContract.SchoolEntry.CONTENT_URI);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ContentValues values= new ContentValues();
        if (intent == null || intent.getAction() == null){
            return;
        }
        switch (intent.getAction()){
            case INSERT_SCHOOLS:
                ArrayList<School> schools = intent.getParcelableArrayListExtra(SCHOOLS_LIST);
                List<ContentValues> schoolsToInsert = new ArrayList<>();

                for (School school : schools){
                    values.put(COLUMN_SCHOOL_NAME, school.getSchoolName());
                    values.put(COLUMN_MATH_SCORE, school.getMathScore());
                    values.put(COLUMN_READING_SCORE, school.getReadingScore());
                    values.put(COLUMN_WRITING_SCORE, school.getWritingScore());
                    values.put(COLUMN_NO_SAT_TAKES, school.getNoOfSatTestTakers());
                    schoolsToInsert.add(values);
                }

                getContentResolver().bulkInsert(NYCSchoolsContract.SchoolEntry.CONTENT_URI,
                        schoolsToInsert.toArray(new ContentValues[schoolsToInsert.size()]));
                break;
            case DELETE_SCHOOLS:
                getContentResolver().delete(CONTENT_URI, null, null);
                getContentResolver().call(CONTENT_URI, METHOD_VACCUM, TABLE_NAME, null);
            default:
                break;
        }
    }
}
