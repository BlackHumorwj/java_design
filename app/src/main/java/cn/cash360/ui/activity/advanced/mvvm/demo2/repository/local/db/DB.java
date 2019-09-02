package cn.cash360.ui.activity.advanced.mvvm.demo2.repository.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import cn.cash360.ui.activity.advanced.mvvm.demo2.User;
import cn.cash360.ui.activity.advanced.mvvm.demo2.repository.local.dao.UserDao;

/**
 * @time 2019/9/2 14:25
 * @desc
 */

//创建数据库

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {

    public abstract UserDao getUserDao();

}
