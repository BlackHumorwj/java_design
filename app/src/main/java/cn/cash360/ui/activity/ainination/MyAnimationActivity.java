package cn.cash360.ui.activity.ainination;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/4/17 13:47
 * @desc
 */

public class MyAnimationActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_animation);
        setTitle("动画");

        startAlphaAnim();
        startScaleAnim();
        startTransAnim();
        startRotateAnim();
    }

    private void startAlphaAnim() {
        TextView ivAlpha = findViewById(R.id.tv_alpha);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_animation);
        animation.setFillAfter(true);
        ivAlpha.startAnimation(animation);
    }

    private void startScaleAnim() {
        TextView ivAlpha = findViewById(R.id.tv_scale);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale_animation);
        animation.setFillAfter(true);
        ivAlpha.startAnimation(animation);
    }

    private void startTransAnim() {
        TextView ivAlpha = findViewById(R.id.tv_trans);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_trans_animation);
        animation.setFillAfter(true);
        ivAlpha.startAnimation(animation);
    }

    private void startRotateAnim() {
        TextView ivAlpha = findViewById(R.id.tv_rotate);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_animation);
        animation.setFillAfter(true);
        ivAlpha.startAnimation(animation);
    }
}
