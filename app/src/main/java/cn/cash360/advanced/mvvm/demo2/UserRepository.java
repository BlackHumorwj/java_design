package cn.cash360.advanced.mvvm.demo2;

import android.content.Context;

import androidx.lifecycle.LiveData;
import cn.cash360.advanced.mvvm.demo2.repository.local.LocalUserDataSource;
import cn.cash360.advanced.mvvm.demo2.repository.remote.RemoteUserDataSource;
import cn.cash360.util.NetworkUtils;

/**
 * @time 2019/9/2 11:26
 * @desc
 */
public class UserRepository {

    private static final UserRepository instance = new UserRepository();
    private UserRepository() {
    }
    public static UserRepository getInstance() {
        return instance;
    }


    private Context context;
    private UserDataSource remoteUserDataSource = RemoteUserDataSource.getInstance();
    private UserDataSource localUserDataSource = LocalUserDataSource.getInstance();

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public LiveData<Lcee<User>> getUser(String username) {
        if (NetworkUtils.isConnected(context)) {
            return remoteUserDataSource.queryByUsername(username);
        } else {
            return localUserDataSource.queryByUsername(username);
        }
    }


}

