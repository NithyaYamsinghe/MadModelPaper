package com.example.modelpaper2020.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.modelpaper2020.User;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "User.db";

    public DBHelper(Context context) { super(context, DATABASE_NAME, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create table
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                UserProfile.Users.COLUMN_NAME_USERNAME + " TEXT," +
                UserProfile.Users.COLUMN_NAME_PASSWORD + " TEXT," +
                UserProfile.Users.COLUMN_NAME_DOB + " TEXT," +
                UserProfile.Users.COLUMN_NAME_GENDER + " TEXT)";


        // execute the content of sql create entries
        db.execSQL(SQL_CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // if the table is already available drop table
        db.execSQL("DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME);

    }

    // add info method
    public boolean addInfo(String username, String password, String dob, String gender) {

        // get the data repository in write mode
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // create new map of values where column names are keys
        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLUMN_NAME_USERNAME, username);
        values.put(UserProfile.Users.COLUMN_NAME_PASSWORD, password);
        values.put(UserProfile.Users.COLUMN_NAME_DOB, dob);
        values.put(UserProfile.Users.COLUMN_NAME_GENDER, gender);

        // insert the new value in to table and returning the primary key value of the new row
        long newRowId = sqLiteDatabase.insert(UserProfile.Users.TABLE_NAME, null, values);

        if (newRowId == -1)
            return false;
        else
            return true;
    }

    // update info method
    public boolean updateInfo(String username, String password, String dob, String gender) {

        // get data repository in readable mode
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        // create new map of values where column names are keys
        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLUMN_NAME_USERNAME, username);
        values.put(UserProfile.Users.COLUMN_NAME_PASSWORD, password);
        values.put(UserProfile.Users.COLUMN_NAME_DOB, dob);
        values.put(UserProfile.Users.COLUMN_NAME_GENDER, gender);

        // which row to update
        String selection = UserProfile.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String selectionArgs[] = {username};

        // update the table and store result status value
        int result = sqLiteDatabase.update(
                UserProfile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        if (result == 1)
            return true;
        else
            return false;
    }

    //  read all info method
    public List readAllInfo() {

        // get data repository in readable mode
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        // projection to get needed columns from database to retrieve
        String projection[] = {
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_NAME_USERNAME,
                UserProfile.Users.COLUMN_NAME_PASSWORD,
                UserProfile.Users.COLUMN_NAME_DOB,
                UserProfile.Users.COLUMN_NAME_GENDER
        };

        // sort the result in descending order
        String sortOrder = UserProfile.Users._ID + " DESC";

        // loop through all the entries in the database
        Cursor cursor = sqLiteDatabase.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);

        List users = new ArrayList<User>();
        while (cursor.moveToNext()) {

            String i = cursor.getColumnName(cursor.getColumnIndexOrThrow(UserProfile.Users._ID));
            String u = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_USERNAME));
            String p = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_PASSWORD));
            String d = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_DOB));
            String g = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_GENDER));

            User ob = new User();
            ob.setId(i);
            ob.setUsername(u);
            ob.setPassword(p);
            ob.setDob(d);
            ob.setGender(g);
            users.add(ob);

        }
        cursor.close();
        return users;
    }

    // read particular user info method
    public User readAllInfo(String username) {

        // get data repository in readable mode
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        // projection to get needed columns from database to retrieve
        String projection[] = {
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_NAME_USERNAME,
                UserProfile.Users.COLUMN_NAME_PASSWORD,
                UserProfile.Users.COLUMN_NAME_DOB,
                UserProfile.Users.COLUMN_NAME_GENDER
        };

        String selection = UserProfile.Users.COLUMN_NAME_USERNAME+ " LIKE ?";
        String selectionArgs[] = {username};
        // sort the result in descending order
        String sortOrder = UserProfile.Users._ID + " DESC";

        // loop through all the entries in the database
        Cursor cursor = sqLiteDatabase.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        User ob = new User();
        while (cursor.moveToNext()) {

            String i = cursor.getColumnName(cursor.getColumnIndexOrThrow(UserProfile.Users._ID));
            String u = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_USERNAME));
            String p = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_PASSWORD));
            String d = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_DOB));
            String g = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_GENDER));


            ob.setId(i);
            ob.setUsername(u);
            ob.setPassword(p);
            ob.setDob(d);
            ob.setGender(g);


        }
        cursor.close();
        return ob;
    }

    // delete info method
    public boolean deleteInfo(String username) {

        // get the data repository in readable mode
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        // which row to delete
        String selection = UserProfile.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String selectionArgs[] = {username};

        // delete the row and return the delete status value
        int result = sqLiteDatabase.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);
        if (result == 1)
            return true;
        else
            return false;
    }
}
