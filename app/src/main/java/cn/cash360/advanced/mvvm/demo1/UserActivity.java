package cn.cash360.advanced.mvvm.demo1;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import cn.cash360.java_design.R;
import cn.cash360.java_design.databinding.MvvmActivityUserABinding;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/9/2 10:23
 * @desc
 */
public class UserActivity extends BaseActivity implements View.OnClickListener {


    //    private TextView tvId;
    //    private TextView tvName;
    private UserViewModel mUserViewModel;
    private MvvmActivityUserABinding mBinding;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, UserActivity.class);
        return intent;
    }

    @Override
    protected void setContent() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.mvvm_activity_user_a);
    }

    @Override
    protected void initView() {
        super.initView();
        //        tvId = (TextView) findViewById(R.id.tv_id);
        //        tvName = (TextView) findViewById(R.id.tv_name);
        mBinding.tvName.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        super.initData();
        //创建UserViewModel实例
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                updateUser(user);
            }
        });
        mUserViewModel.setUserName("张三");

    }

    private void updateUser(User user) {
        if (user != null) {
            mBinding.setUser(user);
            mBinding.executePendingBindings();
        }
    }

    //LiveData 实现 Model 和 View 的绑定。
    private MutableLiveData<String> mCurName;


    public MutableLiveData<String> getCurName() {
        if (mCurName == null) {
            mCurName = new MutableLiveData<>();
        }
        return mCurName;

    }

    int index;

    @Override
    protected void initEvent() {
        super.initEvent();
        final MyLocationLinstener linsener = new MyLocationLinstener(mActivity, getLifecycle(), null);


        //region 监听数据变化
        getCurName().observe(this
                , new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String name) {
                        // Update the UI, in this case, a TextView.
                        //  tvName.setText(name);
                        updateUser(new User(name));
                    }
                });
        //endregion

    }


    @Override
    public void onClick(View v) {
        final int vId = v.getId();
        if (vId == R.id.tv_name) {
            index++;
            mUserViewModel.setUserName("点击测试" + index);
        }


    }
}
