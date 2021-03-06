package cn.cash360.ui.activity.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @time 2019/4/15 10:19
 * @desc
 */

public class BaseActivity extends SwipeBackActivity {

    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = this;
        setContent();
        init();


    }

    protected void setContent() {
        final int resID = getLayoutResID();
        if (resID != -1) {
            setContentView(resID);
        }
    }


    protected void init() {
        initView();
        initData();
        initEvent();
    }

    protected void initEvent() {

    }

    protected void initView() {


    }

    protected void initData() {

    }

    protected int getLayoutResID() {
        return -1;
    }
}
