package com.kahin.lifenglish.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.kahin.lifenglish.SQLite.DBHelper.TABLE_AIRPOET;

/**
 * Created by admin on 17/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    Context mContext = null;

    private static String DB_PATH = "/data/data/com.kahin.lifenglish/databases/";

    private SQLiteDatabase mSQLiteDatabase;

    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "db_main";
    private static final int DB_VERSION = 3;

    public static String TABLE_MAINLIST= "mainlist";
    public static String MAINLIST_ID = "id";
    public static String MAINLIST_NAME = "name";

    public static String TABLE_AIRPOET = "airport";
    public static String ITEM_ID = "id";
    public static String ITEM_CAN= "can";
    public static String ITEM_ENG = "eng";
    public static String ITEM_TCN = "tcn";

    public static final String TABLE_AIR = "air";
    public static final String TABLE_CUSTOMS = "customs";
    public static final String TABLE_SHOPPING = "shopping";
    public static final String TABLE_HOTEL = "hotel";
    public static final String TABLE_RESTAURANT = "restaurant";
    public static final String TABLE_TICKET = "tricket";
    public static final String TABLE_TRAFFIC = "traffic";

    public static final String TYPE_AIRPORT = "機場";
    public static final String TYPE_AIR = "機上";
    public static final String TYPE_CUSTOMS = "入境/海關";
    public static final String TYPE_SHOPPING = "買嘢";
    public static final String TYPE_HOTEL = "酒店";
    public static final String TYPE_RESTAURANT = "食嘢";
    public static final String TYPE_TICKET = "買飛";
    public static final String TYPE_TRAFFIC = "搭車/問路";


    String sql2 = "CREATE TABLE " + TABLE_AIRPOET + "(" + ITEM_ID  + " integer primary key autoincrement , "
            + ITEM_CAN + " text not null, " + ITEM_ENG + " text not null );";

    String sql3 = "DROP TABLE " + TABLE_AIRPOET;

    public DBHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            //database does't exist yet.
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException {
        //Open your local db as the input stream
        InputStream inputStream = mContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream outputStream = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        //Close the streams
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        mSQLiteDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized  void close() {

        if (mSQLiteDatabase != null)
            mSQLiteDatabase.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* String sql = "CREATE TABLE " + TABLE_MAINLIST + "(" + MAINLIST_ID  + " integer primary key autoincrement , "
                + MAINLIST_NAME  + " text not null);";

       try {
            db.execSQL(sql);

            db.execSQL(sql2);
        } catch (SQLException e) {
            return;
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*try {
            //db.execSQL(sql3);

            //db.execSQL(sql2);
        } catch (SQLException e) {
            return;
        }*/

    }



}
