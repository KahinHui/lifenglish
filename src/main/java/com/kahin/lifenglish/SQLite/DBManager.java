package com.kahin.lifenglish.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 數據庫操作管理類
 * Created by admin on 21/5/2017.
 */

public class DBManager {

    private AtomicInteger mOpenCounter = new AtomicInteger();

    private static DBManager instance;

    private static DBHelper helper;
    private static SQLiteDatabase db;

    private DBManager(Context context) {
        helper = new DBHelper(context);
    }

    public static synchronized DBManager getInstance(Context context) {
        if (instance == null) {
            instance = new DBManager(context);
        }

        return instance;
    }

    /**
     * 開啟數據庫
     * @return
     */
    public synchronized  SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            db = helper.getWritableDatabase();
        }

        return db;
    }

    /**
     * 關閉數據庫
     */
    public synchronized  void closeDB() {
        if (mOpenCounter.decrementAndGet() == 0) {
            db.close();
        }
    }

}
