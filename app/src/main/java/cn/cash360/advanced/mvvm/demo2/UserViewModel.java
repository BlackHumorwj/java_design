package cn.cash360.advanced.mvvm.demo2;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

/**
 * @time 2019/9/2 11:31
 * @desc
 */
public class UserViewModel extends ViewModel {


    private UserRepository mUserRepository = UserRepository.getInstance();

    private LiveData<Lcee<User>> mUserLiveData;

    private MutableLiveData<String> mUsernameLiveData;


    public LiveData<Lcee<User>> getUser(String username) {
        if (mUserLiveData == null) {
            mUserLiveData = mUserRepository.getUser(username);
        }
        return mUserLiveData;
    }






    public LiveData<Lcee<User>> getUser(){
        if (mUserLiveData==null){
            mUsernameLiveData = new MutableLiveData<>();
            mUserLiveData = Transformations.switchMap(mUsernameLiveData, new Function<String, LiveData<Lcee<User>>>() {
                @Override
                public LiveData<Lcee<User>> apply(String input) {
                    return mUserRepository.getUser(input);
                }
            });

        }
        return mUserLiveData;
    }


    public void reload(String username){
        mUsernameLiveData.setValue(username);
    }



}
