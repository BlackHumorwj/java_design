package cn.cash360.ui.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @time 2019/4/15 10:19
 * @desc
 */

public class BaseActivity extends AppCompatActivity {

    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = this;
        final int resID = getLayoutResID();
        if (resID != -1) {
            setContentView(resID);
        }
        init();


    }

    protected void init() {

    }

    protected int getLayoutResID() {
        return -1;
    }
}