package com.kahin.lifenglish.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 13/10/2017.
 */

public class SPData {

    Context mContext;

    static SPData instance;

    private static final String NAME_SP = "data";

    SharedPreferences sp;
    SharedPreferences.Editor ed;

    public static final String KEY_TYPE = "keyType";

    public static SPData getInstance(Context context) {
        if (instance == null) {
            instance = new SPData(context);
        }

        return instance;
    }

    private SPData(Context context) {
        this.mContext = context;

        sp = mContext.getSharedPreferences(NAME_SP, mContext.MODE_PRIVATE);
        ed = sp.edit();
    }

    public void save(String name, String value){
        ed.putString(name, value);
        ed.commit();
    }

    public void save(String name, int value){
        ed.putInt(name, value);
        ed.commit();
    }

    public void save(String name, long value){
        ed.putLong(name, value);
        ed.commit();
    }

    public void save(String name, boolean value){
        ed.putBoolean(name, value);
        ed.commit();
    }

    public String getString(String name, String defValue){
        return sp.getString(name, defValue);
    }

    public int getInt(String name, int defValue){
        return sp.getInt(name, defValue);
    }

    public long getLong(String name, long defValue){
        return sp.getLong(name, defValue);
    }

    public boolean getBool(String name, boolean defValue){
        return sp.getBoolean(name, defValue);
    }

}
