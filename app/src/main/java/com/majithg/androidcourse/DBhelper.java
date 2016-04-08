package com.majithg.androidcourse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by majithg on 08-Apr-16.
 */
public class DBhelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1 ;
    private static final String DATABASE_NAME = "Products.db";
    private static final String TABLE_NAME = "Products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCTNAME = "productname";

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCTNAME + "); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
    }

    public void addProduct(Products products){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCTNAME, products.get_productname());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        contentValues.clear();
    }

    public void deleteProduct(String productname){
        SQLiteDatabase sqLiteDatabase2 = getWritableDatabase();


        // plz try to change... id also... try...
//        Cursor cursor = sqLiteDatabase2.rawQuery(" SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " WHERE " + COLUMN_PRODUCTNAME + " = '" + productname + "' ", null);
//        int changeID = cursor.getPosition();
//        cursor.moveToNext();

        sqLiteDatabase2.execSQL(" DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_PRODUCTNAME + " = '" + productname + "'; ");

//        ContentValues contentValues =  new ContentValues();
//        contentValues.put(COLUMN_ID, changeID);

//        cursor.close();
        sqLiteDatabase2.close();
//        contentValues.clear();

    }

    public String databaseToSting(){

        String dbString = "";

        SQLiteDatabase sqLiteDatabase3 = getReadableDatabase();
//        String sqlQuery = " SELECT * FROM "+TABLE_NAME+" WHERE = "+1+"; ";
        String sqlQuery = " SELECT * FROM "+TABLE_NAME ;

        Cursor cursor = sqLiteDatabase3.rawQuery(sqlQuery, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            if(     cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME)) != null     ){

                dbString+=cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME));
                dbString+="\n";
            }
            cursor.moveToNext();
        }
        sqLiteDatabase3.close();
        return dbString;
    }

}
