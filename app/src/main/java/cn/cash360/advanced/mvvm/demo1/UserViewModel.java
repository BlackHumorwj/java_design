package cn.cash360.advanced.mvvm.demo1;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
