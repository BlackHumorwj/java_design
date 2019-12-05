package com.example.aac_mvvm.room.dao;

import com.example.aac_mvvm.room.Order;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * @time 2019/12/3 10:10
 * @desc 数据库访问对象   https://juejin.im/post/5a4228036fb9a044ff31b8ca
 */
@Dao
public interface OrderDao {

    @Query("SELECT * FROM orders")
    List<Order> loadAllOrders();

    @Insert
    void insertAll(Order... orders);


    @Query("Select * From orders where order_id in (:orderIds)")
    List<Order> queryOrderById(long[] orderIds);

    @Delete
    void deleteOrder(Order... orders);

    @Update
    void updateOder(Order... orders);



    //+ livedata
    @Query("select * from orders")
    LiveData<List<Order>> loadAllOrdersLiveData();

    //+RxJava
    @Query("select * from orders")
    Flowable<List<Order>> loadData();

    Maybe<List<Order>>  getMayBe();


}
