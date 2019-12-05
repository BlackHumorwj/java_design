package com.example.aac_mvvm.room.db;

import com.example.aac_mvvm.room.Order;
import com.example.aac_mvvm.room.dao.OrderDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @time 2019/12/3 10:29
 * @desc
 */
@Database(entities = {Order.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OrderDao getOrderDao();

}
