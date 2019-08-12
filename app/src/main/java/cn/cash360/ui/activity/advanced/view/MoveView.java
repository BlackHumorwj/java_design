package cn.cash360.ui.activity.advanced.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.nineoldandroids.view.ViewHelper;

/**
 * @time 2019/8/12 19:38
 * @desc
 */
public class MoveView extends View {

    private float lastX;
    private float lastY;
    private int mScaledTouchSlop;

    public MoveView(Context context) {
        this(context, null);


    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        final float rawX = event.getRawX();
        final float rawY = event.getRawY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltX = (int) (rawX - lastX);
                int deltY = (int) (rawY - lastY);
                if (Math.abs(deltX) < mScaledTouchSlop && Math.abs(deltY) < mScaledTouchSlop) {
                    return false;
                } else {
                    int transX = (int) (ViewHelper.getTranslationX(this) + deltX);
                    int transY = (int) (ViewHelper.getTranslationY(this) + deltY);
                    ViewHelper.setTranslationX(this, transX);
                    ViewHelper.setTranslationY(this, transY);

                }


                break;
            case MotionEvent.ACTION_UP:

                return super.onTouchEvent(event);

        }
        lastX = rawX;
        lastY = rawY;

        return true;
    }
}
