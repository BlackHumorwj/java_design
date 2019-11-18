package cn.cash360.sidazujian;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import cn.cash360.sidazujian.service.MyService;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/4/17 13:47
 * @desc
 */

public class ZuJianActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("四大组件");

        startService(new Intent(mActivity, MyService.class));

    }
}
