package cn.cash360.ui.activity.ainination;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import cn.cash360.java_design.R;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/4/17 13:47
 * @desc
 */

public class MyAnimationActivity extends BaseActivity implements View.OnClickListener {


    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MyAnimationActivity.class);
        return intent;
    }


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_animation);
        setTitle("动画");
        findViewById(R.id.tv_drawable).setOnClickListener(this);
        findViewById(R.id.tv_view).setOnClickListener(this);
        findViewById(R.id.tv_tween_animation).setOnClickListener(this);
        findViewById(R.id.tv_object_animation).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.tv_drawable://
                startActivity(AnimationActivity.newInstance(mActivity, AnimationActivity.DRAWABLE_ANIMATION_FRAGMENT));
                break;
            case R.id.tv_view:
                startActivity(AnimationActivity.newInstance(mActivity, AnimationActivity.VIEW_ANIMATION_FRAGMENT));
                break;
            case R.id.tv_tween_animation:
                startActivity(AnimationActivity.newInstance(mActivity, AnimationActivity.TWEEN_ANIMATION_FRAGMENT));
                break;
            case R.id.tv_object_animation:
                startActivity(AnimationActivity.newInstance(mActivity, AnimationActivity.OBJECT_ANIMATION_FRAGMENT));
                break;
        }
    }
}
