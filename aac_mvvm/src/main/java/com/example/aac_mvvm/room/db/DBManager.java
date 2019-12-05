package com.example.aac_mvvm.room.db;

import android.content.Context;
import android.util.Log;

import com.example.aac_mvvm.AppData;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @time 2019/12/3 10:31
 * @desc
 */
public class DBManager {
    public static class Table {
        public static final String orders = "orders";
    }

    private static AppDatabase DB_INSTANCE = buildDb(AppData.getContext());


    public static AppDatabase getInstance() {
        return DB_INSTANCE;
    }

    private static AppDatabase buildDb(Context context) {

        return Room.databaseBuilder(context, AppDatabase.class, "lion_db")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        Log.e("db","Create version = "+db.getVersion()+" path = "+db.getPath());

                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);

                        Log.e("db","onOpen version"+db.getVersion()+" path = "+db.getPath());
                    }
                })//
                .fallbackToDestructiveMigration()//清楚数据库
//                .addMigrations(new Migration(1, 2) {
//                    @Override
//                    public void migrate(@NonNull SupportSQLiteDatabase database) {
//                        database.execSQL("drop table if exists orders"); //使用drop语句删除一个表，然后重新创建表，表的主键也会重新生成。
//                        //execSQL("DELETE FROM " + TABLE_NAME);    //删除表中的数据，表还是要保留的
//                    }
//                })
                .allowMainThreadQueries()//
                .build();

    }


    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("drop table if exists " + Table.orders); //使用drop语句删除一个表，然后重新创建表，表的主键也会重新生成。
            //execSQL("DELETE FROM " + TABLE_NAME);    //删除表中的数据，表还是要保留的
        }
    };


}
