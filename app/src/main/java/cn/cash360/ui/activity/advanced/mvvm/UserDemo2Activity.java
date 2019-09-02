package cn.cash360.ui.activity.advanced.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.advanced.mvvm.demo2.User;
import cn.cash360.ui.activity.advanced.mvvm.demo2.UserRepository;
import cn.cash360.ui.activity.advanced.mvvm.demo2.UserViewModel;
import cn.cash360.ui.activity.advanced.mvvm.demo2.repository.local.db.DBHelper;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/9/2 10:23
 * @desc
 */
public class UserDemo2Activity extends BaseActivity implements View.OnClickListener {


    private TextView tvId;
    private TextView tvName;
    private UserViewModel mUserViewModel;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, UserDemo2Activity.class);
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
        DBHelper.getInstance().init(this);
        UserRepository.getInstance().init(this);



        //创建UserViewModel实例
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.getUser("ittianyu").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                updateUser(user);
            }
        });

    }

    private void updateUser(User user) {
        if (user!=null){
            tvId.setText(user.getId()+"");
            tvName.setText(user.getName());
        }
    }



    int index;


    @Override
    public void onClick(View v) {
        final int vId = v.getId();
        if (vId == R.id.tv_name) {
            index++;
            //getCurName().setValue("点击测试"+index);
        }



    }
}
