package com.example.ravitej.a20180210_rk_nycschools.common.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class NYCSchoolsContract {

    public static final String CONTENT_AUTHORITY = "com.example.ravitej.a20180210_rk_nycschools";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    static final String PATH_SCHOOL = "schools";

    public static final class SchoolEntry implements BaseColumns {

        //content://com.example.ravitej.a20180210_rk_nycschools/schools
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_SCHOOL).build();

        static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +PATH_SCHOOL;

        static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SCHOOL;

        //table name
        public static final String TABLE_NAME = "schools";

        //columns
        public static final String COLUMN_SCHOOL_NAME = "school_name";
        public static final String COLUMN_MATH_SCORE = "math_score";
        public static final String COLUMN_READING_SCORE = "reading_score";
        public static final String COLUMN_WRITING_SCORE = "writing_score";
        public static final String COLUMN_NO_SAT_TAKES = "sat_test_takers";

        private SchoolEntry() {
            throw new IllegalAccessError("Utility Class");
        }
    }
}
