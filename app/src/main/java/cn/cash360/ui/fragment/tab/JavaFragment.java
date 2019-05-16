package cn.cash360.ui.fragment.tab;

import android.os.Bundle;

import cn.cash360.java_design.R;

/**
 * @time 2019/4/12 12:53
 * @desc
 */

public class JavaFragment extends BaseFragment {

    public static JavaFragment newInstance() {
        Bundle args = new Bundle();
        JavaFragment fragment = new JavaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_primer_layout;
    }
}
