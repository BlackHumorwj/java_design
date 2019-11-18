package cn.cash360.advanced.mvvm.demo2.repository.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import cn.cash360.advanced.mvvm.demo2.User;

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
