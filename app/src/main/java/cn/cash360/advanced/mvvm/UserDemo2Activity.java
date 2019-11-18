package cn.cash360.advanced.mvvm;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import cn.cash360.advanced.mvvm.demo2.Lcee;
import cn.cash360.advanced.mvvm.demo2.User;
import cn.cash360.advanced.mvvm.demo2.UserRepository;
import cn.cash360.advanced.mvvm.demo2.UserViewModel;
import cn.cash360.advanced.mvvm.demo2.repository.local.db.DBHelper;
import cn.cash360.java_design.R;
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
        mUserViewModel.getUser("ittianyu").observe(this, new Observer<Lcee<User>>() {
            @Override
            public void onChanged(@Nullable Lcee<User> user) {
                updateUser(user);
            }
        });

    }

    private void updateUser(Lcee<User> user) {
        if (user!=null){
            tvId.setText(user.data.getId()+"");
            tvName.setText(user.data.getName());
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
