package cn.cash360.ui.activity.ainination;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/5/10 14:15
 * @desc
 */
public class AnimationActivity extends BaseActivity {

    public static final int DRAWABLE_ANIMATION_FRAGMENT = 0;
    public static final int VIEW_ANIMATION_FRAGMENT = 1;
    public static final int TWEEN_ANIMATION_FRAGMENT = 2;


    public static Intent newInstance(Context context, int type) {
        Intent intent = new Intent(context, AnimationActivity.class);
        intent.putExtra("type", type);
        return intent;
    }


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_fl_content;
    }

    @Override
    protected void init() {
        final int type = getIntent().getIntExtra("type", VIEW_ANIMATION_FRAGMENT);
        Fragment fragment = null;
        String title = "";
        switch (type) {
            case DRAWABLE_ANIMATION_FRAGMENT:
                title = "DrawableAnimation";
                fragment = DrawableAnimationFragment.newInstance();
                break;
            case VIEW_ANIMATION_FRAGMENT:
                title = "ViewAnimation";
                fragment = ViewAnimationFragment.newInstance();
                break;
            case TWEEN_ANIMATION_FRAGMENT:
                title = "TweenAnimation";
                fragment = TweenAnimationFragment.newInstance();
                break;
        }

        if (fragment != null) {
            setTitle(title);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment).commit();
        }


    }
}
