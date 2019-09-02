package cn.cash360.ui.activity.advanced.mvvm.demo2.repository.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import cn.cash360.ui.activity.advanced.mvvm.demo2.User;

/**
 * @time 2019/9/2 14:16
 * @desc
 */
//定义Dao操作表
@Dao
public interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long add(User user);

    @Query("select * from user where login = :username")
    LiveData<User> queryByUsername(String username);


}
