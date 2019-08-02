package cn.cash360.ui.fragment.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.cash360.java_design.R;
import cn.cash360.ui.fragment.tab.BaseFragment;

/**
 * @time 2019/5/10 11:55
 * @desc
 */
public class TweenAnimationFragment extends BaseFragment implements View.OnClickListener {


    private LinearLayout mLlRoot;

    public static TweenAnimationFragment newInstance() {
        Bundle args = new Bundle();
        TweenAnimationFragment fragment = new TweenAnimationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_tween_animation;
    }


    protected void initView(View view) {
        mLlRoot = view.findViewById(R.id.ll_root);
        //设置tween动画
        final LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(mActivity, R.anim.anim_tween_animation));
        lac.setDelay(0.5f);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        mLlRoot.setLayoutAnimation(lac);
        view.findViewById(R.id.tv_add).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final TextView textView = new TextView(mActivity);
        textView.setText("顶顶顶顶" + mLlRoot.getChildCount());
        mLlRoot.addView(textView);
    }


}
