package com.kahin.lifenglish.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kahin.lifenglish.SQLite.DBHelper;
import com.kahin.lifenglish.SQLite.DBManager;
import com.kahin.lifenglish.viewModel.ItemListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 5/10/2017.
 */

public class ItemListData {

    Context mContext = null;

    DBManager mDBManager;

    public ItemListData(Context context) { mContext = context; }

    public void instert() {
        mDBManager = DBManager.getInstance(mContext);

        SQLiteDatabase db = null;

        try {
            db = mDBManager.openDatabase();

            db.beginTransaction();

            insert(db, DBHelper.TABLE_AIRPOET, 1, DBHelper.ITEM_CAN, "我要值機航班xxx。", DBHelper.ITEM_ENG, "I am checking in for flight xxx.");
            insert(db, DBHelper.TABLE_AIRPOET, 2, DBHelper.ITEM_CAN, "可以俾我一個靠走廊/靠窗嘅位？", DBHelper.ITEM_ENG, "Could I have an aisle/window seat?");
            insert(db, DBHelper.TABLE_AIRPOET, 3, DBHelper.ITEM_CAN, "我得唔得帶呢個上機？", DBHelper.ITEM_ENG, "Can I carry this on board?");
            insert(db, DBHelper.TABLE_AIRPOET, 4, DBHelper.ITEM_CAN, "呢度係咪航班xxx嘅候機室？", DBHelper.ITEM_ENG, "Is this the right lounge for xxx?");
            insert(db, DBHelper.TABLE_AIRPOET, 5, DBHelper.ITEM_CAN, "過境班機幾時起飛？", DBHelper.ITEM_ENG, "When will the transit flight take off?");
            insert(db, DBHelper.TABLE_AIRPOET, 6, DBHelper.ITEM_CAN, "所有轉機嘅旅客，請前往8號登機口。", DBHelper.ITEM_ENG, "All connecting passengers are requested to proceed to Gate 8.");
            insert(db, DBHelper.TABLE_AIRPOET, 7, DBHelper.ITEM_CAN, "行李認領處係邊？", DBHelper.ITEM_ENG, "Where is the baggage claim?");
            insert(db, DBHelper.TABLE_AIRPOET, 8, DBHelper.ITEM_CAN, "xxx航班嘅行李領取處係邊？", DBHelper.ITEM_ENG, "Which carousel is for flight xxx?");
            insert(db, DBHelper.TABLE_AIRPOET, 9, DBHelper.ITEM_CAN, "行李手推車係邊？", DBHelper.ITEM_ENG, "Where is the baggage trolley?");
            insert(db, DBHelper.TABLE_AIRPOET, 10, DBHelper.ITEM_CAN, "我想退咗呢飛。", DBHelper.ITEM_ENG, "I would like a refund on this ticket.");

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();

            mDBManager.closeDB();
        }
    }

    private void insert(SQLiteDatabase db, String table, String key, String value) {

        ContentValues cv = new ContentValues();
        cv.put(key, value);

        db.insert(table, null, cv);

    }

    private void insert(SQLiteDatabase db, String table, int id, String key1, String value1, String key2, String value2) {
        db.execSQL("insert into " + table + "(id," + key1 + "," + key2 + ") values("+ id + ",'" + value1 + "', '" + value2 + "')");
    }

    public List<ItemListViewModel> queryItemList(String tableName) {

        List<ItemListViewModel> items = new ArrayList<ItemListViewModel>();

        mDBManager = DBManager.getInstance(mContext);

        SQLiteDatabase db = null;

        try {
            db = mDBManager.openDatabase();

            db.beginTransaction();

            Cursor cursor = db.rawQuery("select * from " + tableName, null);

            try {
                while (cursor.moveToNext()) {
                    String content = cursor.getString(cursor.getColumnIndex(DBHelper.ITEM_CAN));

                    int id = cursor.getInt(cursor.getColumnIndex(DBHelper.ITEM_ID));

                    items.add(new ItemListViewModel(id, content));
                }
            } finally {
                cursor.close();
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();

                mDBManager.closeDB();
            }
        }

        return items;
    }

    public String queryByCn(String tableName, String str) {
        String eng = "";

        mDBManager = DBManager.getInstance(mContext);

        SQLiteDatabase db = null;

        try {
            db = mDBManager.openDatabase();

            db.beginTransaction();

            Cursor cursor = db.rawQuery("select * from " + tableName + " where can like ? ", new String[]{str});

            try {
                while (cursor.moveToNext()) {
                    eng = cursor.getString(cursor.getColumnIndex(DBHelper.ITEM_ENG));

                    //int id = cursor.getInt(cursor.getColumnIndex(DBHelper.ITEM_ID));

                }
            } finally {
                cursor.close();
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();

                mDBManager.closeDB();
            }
        }

        return eng;
    }
}
