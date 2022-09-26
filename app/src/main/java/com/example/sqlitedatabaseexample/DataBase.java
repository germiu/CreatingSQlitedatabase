package com.example.sqlitedatabaseexample;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName";
    public static final String CONTACTS_TABLE_NAME = "Information";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "username";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_DOOR = "door";
    public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_COUNTRY = "country";



    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table information" + "(id interger primary key,username text," +
                "phone text,email text,door text,place text,country text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertContact (String username,String phone,String email, String door, String place, String country){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("phone",phone);
        contentValues.put("email",email);
        contentValues.put("door",door);
        contentValues.put("place",place);
        contentValues.put("country",country);
        db.insert("information",null,contentValues);
        return true;
    }
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from information where id ="+id+"",null);
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,CONTACTS_TABLE_NAME);
        return numRows;
    }

    @SuppressLint("Range")
    public ArrayList<String> getInformation(){
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from information",null);
        res.moveToFirst();

        while (res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex((CONTACTS_COLUMN_NAME))));
            res.moveToNext();
        }
        return array_list;
    }
}
