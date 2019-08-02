package cn.cash360.ui.fragment.tab;

import android.os.Bundle;
import android.view.View;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.advanced.handler.HandlerActivity;

/**
 * @time 2019/4/12 12:53
 * @desc
 */

public class AdvancedFragment extends BaseFragment implements View.OnClickListener {

    public static AdvancedFragment newInstance() {
        Bundle args = new Bundle();
        AdvancedFragment fragment = new AdvancedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advanced_layout;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        view.findViewById(R.id.tv_handler).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final int vId = v.getId();
        if (vId==R.id.tv_handler){
            startActivity(HandlerActivity.newInstance(mActivity));
        }else {

        }

    }
}
