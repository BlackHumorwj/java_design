package cn.cash360.ui.activity.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @time 2019/4/15 9:05
 * @desc 定义一个类似线性布局
 */

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int getTotalHeight() {
        int totalHeight = 0;
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            totalHeight += child.getMeasuredHeight();
        }
        return totalHeight;
    }

    private int getMaxChildWidth() {
        int maxHeight = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (maxHeight < child.getMeasuredHeight()) {
                maxHeight = child.getMeasuredHeight();
            }
        }
        return maxHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //1、将所有的子View进行测量，这会触发 每个子View 的onMeasure 函数
        //注意与measureChild()区分，measureChild 是对单个view进行测量;
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //2、根据ViewGroup测量模式组合，设置ViewGroup的宽高

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int childCount = getChildCount();

        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            //各种模式组合

            //宽度和高度都是包裹
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                int width = getMaxChildWidth();
                int height = getTotalHeight();
                setMeasuredDimension(width, height);
                //宽度包裹
            } else if (widthMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(getMaxChildWidth(), heightSize);
                //高度包裹
            } else if (heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSize, getTotalHeight());
            }
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();

        //记录当前的高度
        int curHeight = t;

        //3、遍历子View 布置子View
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();

            child.layout(l, curHeight, l + width, curHeight + height);

            //累计子View的高度
            curHeight += height;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
