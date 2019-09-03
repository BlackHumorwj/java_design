package cn.cash360.ui.activity.advanced.mvvm.demo2.repository.local.service;

import android.arch.lifecycle.LiveData;

import cn.cash360.ui.activity.advanced.mvvm.demo2.User;

/**
 * @time 2019/9/2 14:37
 * @desc
 */
public interface UserService {

    LiveData<Long> addUser(User user);


    LiveData<User> queryByUsername(String userName);

}