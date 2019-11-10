package com.kahin.lifenglish.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.kahin.lifenglish.SQLite.DBManager;
import com.kahin.lifenglish.viewModel.MainListViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.kahin.lifenglish.SQLite.DBHelper.MAINLIST_NAME;
import static com.kahin.lifenglish.SQLite.DBHelper.TABLE_MAINLIST;

/**
 * Created by admin on 15/5/2017.
 */

public class MainListData {

    Context mContext = null;

    public List<MainListViewModel> mainlist;

    //public final ObservableField<List<MainListViewModel>> mDatas = new ObservableField<>();

    //public final ObservableField<String> description = new ObservableField<>();
    //public final ObservableField<String> imageUrl = new ObservableField<>();

    DBManager mDBManager;

    public MainListData(Context context) {
        mContext = context;
    }

    public MainListData(Context context, MainListViewModel data) {
        mContext = context;
        //description.set(data.description);
        //imageUrl.set(data.imageUrl);

    }

    public void instert() {
        mDBManager = DBManager.getInstance(mContext);

        SQLiteDatabase db = null;

        try {
            db = mDBManager.openDatabase();

            db.beginTransaction();

            insert(db, TABLE_MAINLIST, MAINLIST_NAME, "機場");
            insert(db, TABLE_MAINLIST, MAINLIST_NAME, "機上");
            insert(db, TABLE_MAINLIST, MAINLIST_NAME, "入境/海關");
            insert(db, TABLE_MAINLIST, MAINLIST_NAME, "搭車/問路");
            insert(db, TABLE_MAINLIST, MAINLIST_NAME, "酒店");
            insert(db, TABLE_MAINLIST, MAINLIST_NAME, "食嘢");
            insert(db, TABLE_MAINLIST, MAINLIST_NAME, "買嘢");
            insert(db, TABLE_MAINLIST, MAINLIST_NAME, "買飛");

            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();

            mDBManager.closeDB();
        }

    }

    private void insert(SQLiteDatabase db, String table, String key, String value) {
        //SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(key, value);

        db.insert(table, null, cv);

        //db.close();
    }

    public void queryMainList() {
        mDBManager = DBManager.getInstance(mContext);

        SQLiteDatabase db = null;

        try {
            db = mDBManager.openDatabase();

            db.beginTransaction();

            Cursor c = db.query(TABLE_MAINLIST, new String[]{"*"}, MAINLIST_NAME + "=?", new String[]{"機上"}, null, null, null);
            //Cursor c = db.query(TABLE_MAINLIST, new String[]{DBHelper.MAINLIST_NAME}, null, null, null, null, null);


            Toast.makeText(mContext, "name:"+c.getCount(), Toast.LENGTH_LONG).show();
            String a = "";
            try {
                while (c.moveToNext()) {
                    String n = c.getString(c.getColumnIndex(MAINLIST_NAME));
                    a = a + n;
                }
            } finally {
                c.close();
                Toast.makeText(mContext, "name:"+a, Toast.LENGTH_LONG).show();
            }

            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "e:"+e, Toast.LENGTH_LONG).show();
        } finally {
            if (db != null) {
                db.endTransaction();

                mDBManager.closeDB();
            }
        }

    }

    public List<MainListViewModel> queryMainListReturn() {
        List<MainListViewModel> items = new ArrayList<MainListViewModel>();

        mDBManager = DBManager.getInstance(mContext);

        SQLiteDatabase db = null;

        try {
            db = mDBManager.openDatabase();

            db.beginTransaction();

            Cursor cursor = db.rawQuery("select * from " + TABLE_MAINLIST, null);

            try {
                while (cursor.moveToNext()) {
                    String description = cursor.getString(cursor.getColumnIndex(MAINLIST_NAME));

                    items.add(new MainListViewModel(description, ""));

                    //Toast.makeText(mContext, description, Toast.LENGTH_SHORT).show();
                }
            } finally {
                cursor.close();
            }

            db.setTransactionSuccessful();

            //db.close();

        } catch (SQLiteException e) {

        } finally {
            if (db != null) {
                db.endTransaction();

                mDBManager.closeDB();
            }
        }

        return items;
    }
}
