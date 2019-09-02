package cn.cash360.ui.activity.advanced.mvvm.demo1;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @time 2019/9/2 10:37
 * @desc
 */
public class UserViewModel extends ViewModel {


   private MutableLiveData<User> mUserMutableLiveData;


    public MutableLiveData<User> getUser() {
        if (mUserMutableLiveData == null) {
            mUserMutableLiveData = new MutableLiveData<>();
        }
        return mUserMutableLiveData;
    }

    public void setUserName(String userName){

        mUserMutableLiveData.setValue(new User(userName));


    }

}
