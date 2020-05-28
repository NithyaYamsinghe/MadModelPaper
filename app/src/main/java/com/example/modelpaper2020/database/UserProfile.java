package com.example.modelpaper2020.database;

import android.provider.BaseColumns;

public final class UserProfile {

    private UserProfile() {
    }

    public class Users implements BaseColumns {

        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_NAME_USERNAME = "userName";
        public static final String COLUMN_NAME_PASSWORD= "password";
        public static final String COLUMN_NAME_DOB = "dateOfBirth";
        public static  final String COLUMN_NAME_GENDER = "gender";

    }
}
