package cn.cash360.ui.fragment.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import cn.cash360.java_design.R;
import cn.cash360.ui.fragment.tab.BaseFragment;

/**
 * @time 2019/5/10 11:55
 * @desc
 */
public class DrawableAnimationFragment extends BaseFragment {
    public static DrawableAnimationFragment newInstance() {
        Bundle args = new Bundle();
        DrawableAnimationFragment fragment = new DrawableAnimationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_drawable_animation;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(mContentView);
    }

    private void initView(View view) {
        ImageView ivBg = view.findViewById(R.id.iv_bg);
        ivBg.setImageResource(R.drawable.anim_drawable_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) ivBg.getDrawable();
        animationDrawable.start();
    }


}
