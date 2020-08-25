package com.demo.definelabtest.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.demo.definelabtest.Model.SQLSavedMatchesModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MatchesManager";
    private static final String TABLE_CONTACTS = "matches";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addMatch(SQLSavedMatchesModel sqlSavedMatchesModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,sqlSavedMatchesModel.getId()); // SQLSavedMatchesModel Name
        values.put(KEY_NAME,sqlSavedMatchesModel.getName()); // SQLSavedMatchesModel Phone
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // Deleting single contact
    public void deleteMatch(SQLSavedMatchesModel sqlSavedMatchesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(sqlSavedMatchesModel.getId()) });
        db.close();
    }

    // code to get all contacts in a list view  
    public List<SQLSavedMatchesModel> getAllSQLSavedMatchesModels() {
        List<SQLSavedMatchesModel> sqlSavedMatchesModelArrayList = new ArrayList<SQLSavedMatchesModel>();
        // Select All Query  
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list  
        if (cursor.moveToFirst()) {
            do {
                SQLSavedMatchesModel matchesModel = new SQLSavedMatchesModel();
                matchesModel.setId(cursor.getString(0));
                matchesModel.setName(cursor.getString(1));

                // Adding contact to list  
                sqlSavedMatchesModelArrayList.add(matchesModel);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return contact list  
        return sqlSavedMatchesModelArrayList;
    }

}
