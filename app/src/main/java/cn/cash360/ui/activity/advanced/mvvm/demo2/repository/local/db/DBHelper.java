package cn.cash360.ui.activity.advanced.mvvm.demo2.repository.local.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * @time 2019/9/2 14:29
 * @desc
 */
public class DBHelper {

    private static DBHelper sDBHelper = new DBHelper();
    private static final String DATABASE_NAME = "c_cache";

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
