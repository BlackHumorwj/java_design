package cn.cash360.advanced.mvvm.demo2.repository.local.service;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.cash360.advanced.mvvm.demo2.User;
import cn.cash360.advanced.mvvm.demo2.repository.local.dao.UserDao;
import cn.cash360.advanced.mvvm.demo2.repository.local.db.DBHelper;

/**
 * @time 2019/9/2 14:41
 * @desc
 */
public class UserServiceImpl implements UserService {


    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }



    private UserDao mUserDao = DBHelper.getInstance().getDB().getUserDao();



    @SuppressLint("StaticFieldLeak")
    @Override
    public LiveData<Long> addUser(final User user) {
        final MutableLiveData<Long> userMutableLiveData = new MutableLiveData<>();

        new AsyncTask<Void,Void,Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                return mUserDao.add(user);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                userMutableLiveData.setValue(aLong);

            }
        }.execute();
        return userMutableLiveData;
    }

    @Override
    public LiveData<User> queryByUsername(String userName) {
        return mUserDao.queryByUsername(userName);
    }
}
