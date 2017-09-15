package com.kahin.lifenglish.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by admin on 17/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    Context mContext = null;

    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "db_main";
    public static String TABLE_MAINLIST= "mainlist";
    public static String MAINLIST_ID = "id";
    public static String MAINLIST_NAME= "name";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_MAINLIST + "(" + MAINLIST_ID  + " integer primary key autoincrement , "
                + MAINLIST_NAME  + " text not null);";

        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            return;
        }

        /*db.beginTransaction();

        insert(db, TABLE_MAINLIST, MAINLIST_NAME, "機場");
        insert(db, TABLE_MAINLIST, MAINLIST_NAME, "機上");
        insert(db, TABLE_MAINLIST, MAINLIST_NAME, "入境/海關");
        insert(db, TABLE_MAINLIST, MAINLIST_NAME, "搭車/問路");
        insert(db, TABLE_MAINLIST, MAINLIST_NAME, "酒店");
        insert(db, TABLE_MAINLIST, MAINLIST_NAME, "食嘢");
        insert(db, TABLE_MAINLIST, MAINLIST_NAME, "買嘢");
        insert(db, TABLE_MAINLIST, MAINLIST_NAME, "買飛");



        db.setTransactionSuccessful();



        db.endTransaction();

        db.close();*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
