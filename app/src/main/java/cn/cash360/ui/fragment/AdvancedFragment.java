package cn.cash360.ui.fragment;

import android.os.Bundle;

import cn.cash360.java_design.R;

/**
 * @time 2019/4/12 12:53
 * @desc
 */

public class AdvancedFragment extends BaseFragment {

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
}
