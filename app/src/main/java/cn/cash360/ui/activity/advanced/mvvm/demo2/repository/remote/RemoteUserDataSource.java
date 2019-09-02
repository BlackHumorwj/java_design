package cn.cash360.ui.activity.advanced.mvvm.demo2.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.SystemClock;

import cn.cash360.ui.activity.advanced.mvvm.demo2.User;
import cn.cash360.ui.activity.advanced.mvvm.demo2.UserDataSource;
import cn.cash360.ui.activity.advanced.mvvm.demo2.repository.local.LocalUserDataSource;

/**
 * @time 2019/9/2 15:15
 * @desc
 */
public class RemoteUserDataSource implements UserDataSource {


    private static final RemoteUserDataSource mDataSource = new RemoteUserDataSource();


    private RemoteUserDataSource() {
    }


    public static RemoteUserDataSource getInstance() {
        return mDataSource;
    }


    @Override
    public LiveData<User> queryByUsername(String username) {


        final MutableLiveData<User> liveData = new MutableLiveData<>();

        SystemClock.sleep(2000);

        final User user = new User();
        user.setName("服务端数据"+SystemClock.currentThreadTimeMillis());
        user.setId(2012355);
        liveData.setValue(user);

        // update cache
        LocalUserDataSource.getInstance().addUser(user);


        //        mUserApi.queryUserByUserName(userName)
        //                .enqueue(new Callback<User>() {
        //                    @Override
        //                    public void onResponse(Call<User> call, Response<User> response) {
        //
        //                        Log.e("xxx",response.toString());
        //
        //
        //
        //                    }
        //
        //                    @Override
        //                    public void onFailure(Call<User> call, Throwable t) {
        //
        //                    }
        //                });

        //        try {
        //            final Response<User> execute = mUserApi.queryUserByUserName(userName).execute();
        //            liveData.setValue(execute.body());
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }


        return liveData;
    }

    @Override
    public LiveData<Long> addUser(User user) {
        return null;
    }
}
