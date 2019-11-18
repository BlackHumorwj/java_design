package cn.cash360.ui.fragment.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.cash360.java_design.R;
import cn.cash360.ui.fragment.tab.BaseFragment;

/**
 * @time 2019/5/10 11:55
 * @desc
 */
public class ViewAnimationFragment extends BaseFragment {
    public static ViewAnimationFragment newInstance() {
        Bundle args = new Bundle();
        ViewAnimationFragment fragment = new ViewAnimationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_view_animation;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startAlphaAnim();
        startScaleAnim();
        startTransAnim();
        startRotateAnim();

    }


    private void startAlphaAnim() {
        TextView ivAlpha = mContentView.findViewById(R.id.tv_alpha);
        final Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.anim_alpha_animation);
        animation.setFillAfter(true);
        ivAlpha.startAnimation(animation);
    }

    private void startScaleAnim() {
        TextView ivAlpha = mContentView.findViewById(R.id.tv_scale);
        final Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.anim_scale_animation);
        animation.setFillAfter(true);
        ivAlpha.startAnimation(animation);
    }

    private void startTransAnim() {
        TextView ivAlpha = mContentView.findViewById(R.id.tv_trans);
        final Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.anim_trans_animation);
        animation.setFillAfter(true);
        ivAlpha.startAnimation(animation);
    }

    private void startRotateAnim() {
        TextView ivAlpha = mContentView.findViewById(R.id.tv_rotate);
        final Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.anim_rotate_animation);
        animation.setFillAfter(true);
        ivAlpha.startAnimation(animation);
    }


}
