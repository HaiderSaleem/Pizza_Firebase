package com.example.mhaidersaleem.pizza_admin;

/**
 * Created by M.HAiDER Saleem on 25/04/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Counter";
    // table name
    private static final String TABLE_CONTACTS = "Table_counter";


    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "Name";


    //CV COLUMNS


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT"
                 + ")";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(sqLiteDatabase);

    }

    // Adding new contact
    Long addContact(String name, String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        // Inserting Row
        long id = db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
        return id;
    }

    //adding cv


    int check(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor query = db.query(TABLE_CONTACTS, new String[]{KEY_ID}, "", new String[]{email, password}, null, null, null, null);
        int id = -1;

        if (query.getCount() > 0) {
            if (query.moveToFirst()) {
                do {
                    id = query.getInt(query.getColumnIndex(KEY_ID));
                    db.close();

                    return id;
                } while (query.moveToNext());

            } else {

                db.close();
                id = -2;
                return id;
            }


        } else
            return id;
    }

}
