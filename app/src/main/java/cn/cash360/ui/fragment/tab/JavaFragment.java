package cn.cash360.ui.fragment.tab;

import android.os.Bundle;
import android.view.View;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.java.ThreadActivity;

/**
 * @time 2019/4/12 12:53
 * @desc
 */

public class JavaFragment extends BaseFragment implements View.OnClickListener {

    public static JavaFragment newInstance() {
        Bundle args = new Bundle();
        JavaFragment fragment = new JavaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_java_layout;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        view.findViewById(R.id.tv_thread).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int vId = v.getId();
        if (vId == R.id.tv_thread) {
            startActivity(ThreadActivity.newInstance(mActivity));
        }
    }
}
