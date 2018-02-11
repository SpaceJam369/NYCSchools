package com.example.ravitej.a20180210_rk_nycschools.common.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.CONTENT_URI;
import static com.example.ravitej.a20180210_rk_nycschools.common.data.NYCSchoolsContract.SchoolEntry.TABLE_NAME;

public class NYCSchoolsProvider extends ContentProvider{

    static final int SCHOOLS = 100;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private NYCSchoolsDBHelper mDBHelper;
    public static final String METHOD_VACCUM = "vaccum";

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = NYCSchoolsContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, NYCSchoolsContract.PATH_SCHOOL, SCHOOLS);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new NYCSchoolsDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db= mDBHelper.getReadableDatabase();
        Cursor cursor;

        switch (sUriMatcher.match(uri)){
            case SCHOOLS:
                cursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
                default:
                    throw new UnsupportedOperationException("Unknown uri query: "+uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match){
            case SCHOOLS:
                return NYCSchoolsContract.SchoolEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown Uri: "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match){
            case SCHOOLS:
                long returnId = db.insert(TABLE_NAME, null, contentValues);
                if (returnId > 0){
                    returnUri = ContentUris.withAppendedId(CONTENT_URI, returnId);
                }else{
                    throw new android.database.SQLException("Failed to insert school details in database "+uri);
                }
               break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: "+uri);
        }

        if (getContext() != null){
            getContext().getContentResolver().notifyChange(uri, null, false);
        }

        return returnUri;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        String tableName;
        int count;

        switch (match){
            case SCHOOLS:
                tableName = TABLE_NAME;
                break;
            default:
               return super.bulkInsert(uri, values);
        }

        db.beginTransaction();
        count = 0;

        try{
            for (ContentValues value : values){
                long id = db.insert(tableName, null, value);
                if (id != -1){
                    count++;
                }
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }

        if (getContext() != null){
            getContext().getContentResolver().notifyChange(uri, null, false);
        }

        return count;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        final int matcher = sUriMatcher.match(uri);

        int deletedRecords;

        switch (matcher){
            case SCHOOLS:
                deletedRecords = db.delete(TABLE_NAME, s, strings);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri in delete " + uri);
        }

        return deletedRecords;
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        switch (method){
            case METHOD_VACCUM:
                vacuum(arg);
                return null;
            default:
                return super.call(method, arg, extras);
        }
    }

    //vacuum is used to free unused memory space of database.
    private void vacuum(@NonNull String tableName) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        final String SQL_VACUUM_TABLE = "VACUUM " +tableName;
        db.execSQL(SQL_VACUUM_TABLE);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
