package cn.cash360.advanced.mvvm.demo3.module.local.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * @time 2019/9/3 11:42
 * @desc
 */
public class DBHelper {

    private static DBHelper sDBHelper = new DBHelper();
    private static final String DATABASE_NAME = "load_more";

    private DBHelper() {

    }


    public static DBHelper getInstance() {
        return sDBHelper;
    }


    private DB mDB;


    public void init(Context context) {
        mDB = Room.databaseBuilder(context.getApplicationContext(), DB.class, DATABASE_NAME).build();
    }


    public DB getDB() {
        return mDB;
    }


}
