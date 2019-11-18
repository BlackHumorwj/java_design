package cn.cash360.advanced.mvvm.demo2;


import androidx.lifecycle.LiveData;

/**
 * @time 2019/9/2 15:08
 * @desc 统一数据源
 */
public interface UserDataSource {


    LiveData<Lcee<User>> queryByUsername(String username);

    LiveData<Long> addUser(User user);
}
