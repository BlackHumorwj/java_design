package com.example.aac_mvvm.liveDataVM;

import com.example.aac_mvvm.bean.UserBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @time 2019/12/2 19:49
 * @desc
 */
public class UserViewModel extends ViewModel implements BaseViewModel<UserBean> {


    private MutableLiveData<UserBean> mLiveData;


    public MutableLiveData<UserBean> getLiveData() {
        if (mLiveData == null) {
            mLiveData = new MutableLiveData<>();
        }
        return mLiveData;
    }


    @Override
    public UserBean loadData() {
        final UserBean userBean = new UserBean();
        userBean.name = "lisi";
        userBean.userId = "1001";
        userBean.phone = "14400001236";
        return userBean;
    }

    @Override
    public void clearData() {

    }

    public void changeData() {
        if (mLiveData!=null){
            mLiveData.setValue(loadData());
        }
    }
}
