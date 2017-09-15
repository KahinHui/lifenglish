package com.kahin.lifenglish.viewModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.kahin.lifenglish.SQLite.DBManager;

import java.util.ArrayList;

import static com.kahin.lifenglish.SQLite.DBHelper.MAINLIST_NAME;
import static com.kahin.lifenglish.SQLite.DBHelper.TABLE_MAINLIST;

/**
 * Created by admin on 21/5/2017.
 */

public class MainListViewModel extends ViewModel{

    Context mContext = null;

    //private MainLsitData mMainLsitData = new MainLsitData();

    public final ObservableField<ArrayList> mainlist = new ObservableField<>();



    public MainListViewModel(Context context) {
        mContext = context;
    }



}
