package ir.sample.sqlitesample.model.data_access;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ir.sample.sqlitesample.SampleDatabaseHelper;
import ir.sample.sqlitesample.model.table_object.User;

//This Class Contains  Methods : We Need Them To Access Database And Columns Of Table Users ;
public class User_DataAccess {
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    //This List Contains Columns Of TableUsers;
    private String[] userColumns = {SampleDatabaseHelper.USERS_ID, SampleDatabaseHelper.USERS_NAME, SampleDatabaseHelper.USERS_EMAIL};

    public User_DataAccess(Context context) {
        sqLiteOpenHelper = new SampleDatabaseHelper(context);
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();

        //This Cursor Contains an Query ;
        //Query Returns :  All Columns in (userColumns) From TableUsers ;
        Cursor cursor = database.query(SampleDatabaseHelper.TABLE_USERS, userColumns, null, null, null, null, null);

        // cursor.moveToFirst() Check Cursor :  ValidRecord (true) Or InvalidRecord (false) ;
        if (cursor.moveToFirst()) {
            do {
                //Initialize User Object And Fetch Data From Cursor ;
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(SampleDatabaseHelper.USERS_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(SampleDatabaseHelper.USERS_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(SampleDatabaseHelper.USERS_EMAIL)));
                //Fill userList With Fetch Data From Cursor ;
                userList.add(user);
            }
            while (cursor.moveToNext());
            // cursor.moveToNext() Check Cursor :  Valid Next Record (true : Back to Loop) Or Invalid Next Record (false : Stop Loop And Exit) ;

        }

        return userList;
    }

    public void addUser(User user) {
        //Set  ContentValues ::  ContentValues Base Is HashMap [key , value] ;
        ContentValues contentValues = new ContentValues();
        //Pass ( Key: USER_NAME)  And  (value: getMethod) ;
        contentValues.put(SampleDatabaseHelper.USERS_NAME, user.getName());
        contentValues.put(SampleDatabaseHelper.USERS_EMAIL, user.getEmail());
        // Insert This ContentValues Data intro Database ;
        database.insert(SampleDatabaseHelper.TABLE_USERS, null, contentValues);

    }

    public void open() {
        //Preparing And Open Database For Write In Database;
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        //Close Database ;
        database.close();
    }

}
