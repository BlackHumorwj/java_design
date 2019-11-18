package cn.cash360.advanced.mvvm.demo2.repository.local;


import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import cn.cash360.advanced.mvvm.demo2.Lcee;
import cn.cash360.advanced.mvvm.demo2.User;
import cn.cash360.advanced.mvvm.demo2.UserDataSource;
import cn.cash360.advanced.mvvm.demo2.repository.local.service.UserServiceImpl;

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
    public LiveData<Lcee<User>> queryByUsername(String username) {

        //可以添加多个数据源
        final MediatorLiveData<Lcee<User>> mediatorLiveData = new MediatorLiveData<>();

        mediatorLiveData.setValue(Lcee.<User>loading());

        mediatorLiveData.addSource(mUserService.queryByUsername(username), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user==null){
                    mediatorLiveData.setValue(Lcee.empty(user));
                }else {
                    mediatorLiveData.setValue(Lcee.content(user));
                }
            }
        });



        return mediatorLiveData;
    }

    @Override
    public LiveData<Long> addUser(User user) {
        return mUserService.addUser(user);
    }
}
