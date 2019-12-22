package ir.sample.sqlitesample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleDatabaseHelper extends SQLiteOpenHelper {
//Design Database
    private static String DATABASE_NAME = "database.db";
    private static int DATABASE_VERSION = 1;
//Design Table "users" And Columns
    public static final String TABLE_USERS = "users";
    public static final String USERS_ID = "id" ;
    public static final String USERS_NAME = "name";
    public static final String USERS_EMAIL = "email";



    public SampleDatabaseHelper(Context context) {
//Set Constructor And Super
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//usage : Create Tables Of Database ;
//Query : Create Table "users" ;
        db.execSQL("CREATE TABLE IF NOT EXISTS"+" "+TABLE_USERS + " ( "+
                           USERS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                           USERS_NAME+" TEXT NOT NULL , "+
                           USERS_EMAIL+" TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersionDatabase, int newVersionDatabase) {
//usage : Upgrade Database  (from Version Of Database) ;
//Query : Drop Table "users" ;

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_USERS);
//run method : Create New Table ;
        onCreate(db);
    }
}
