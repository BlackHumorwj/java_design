package cn.cash360.ui.activity.advanced.mvvm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.advanced.mvvm.demo1.MyLocationLinsener;
import cn.cash360.ui.activity.advanced.mvvm.demo1.User;
import cn.cash360.ui.activity.advanced.mvvm.demo1.UserViewModel;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/9/2 10:23
 * @desc
 */
public class UserActivity extends BaseActivity implements View.OnClickListener {


    private TextView tvId;
    private TextView tvName;
    private UserViewModel mUserViewModel;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, UserActivity.class);
        return intent;
    }


    @Override
    protected int getLayoutResID() {
        return R.layout.mvvm_activity_user_a;
    }

    @Override
    protected void initView() {
        super.initView();
        tvId = (TextView) findViewById(R.id.tv_id);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvName.setOnClickListener(this);
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
        if (user!=null){
            tvId.setText(user.getId()+"");
            tvName.setText(user.getName());
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
        final MyLocationLinsener linsener = new MyLocationLinsener(mActivity, getLifecycle(), null);


        //region 监听数据变化
        getCurName().observe(this
                , new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String name) {
                        // Update the UI, in this case, a TextView.
                        tvName.setText(name);
                    }
                });
        //endregion

    }


    @Override
    public void onClick(View v) {
        final int vId = v.getId();
        if (vId == R.id.tv_name) {
            index++;
            //getCurName().setValue("点击测试"+index);

            mUserViewModel.setUserName("点击测试"+index);
        }



    }
}
