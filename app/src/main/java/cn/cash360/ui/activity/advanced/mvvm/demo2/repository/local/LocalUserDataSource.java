package cn.cash360.ui.activity.advanced.mvvm.demo2.repository.local;

import android.arch.lifecycle.LiveData;

import cn.cash360.ui.activity.advanced.mvvm.demo2.User;
import cn.cash360.ui.activity.advanced.mvvm.demo2.UserDataSource;
import cn.cash360.ui.activity.advanced.mvvm.demo2.repository.local.service.UserServiceImpl;

/**
 * @time 2019/9/2 15:11
 * @desc
 */
public class LocalUserDataSource implements UserDataSource {


    private static final LocalUserDataSource mDataSource = new LocalUserDataSource();


    private LocalUserDataSource() {
    }


    public static LocalUserDataSource getInstance() {
        return mDataSource;
    }


    private UserServiceImpl mUserService = UserServiceImpl.getInstance();


    @Override
    public LiveData<User> queryByUsername(String username) {
        return mUserService.queryByUsername(username);
    }

    @Override
    public LiveData<Long> addUser(User user) {
        return mUserService.addUser(user);
    }
}
