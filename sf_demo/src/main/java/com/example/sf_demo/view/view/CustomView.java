package com.example.sf_demo.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @time 2019/12/9 15:17
 * @desc
 */
public class CustomView extends ViewGroup {

    private Paint mPaint;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //遍历测量子View，调用子View#measure()方法
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //1、设置控件最后的测量大小,
        //2、此方法调用后，可以通过 getMeasuredWidth()获取View的宽高
        //setMeasuredDimension(100,100);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childCount = getChildCount();
        if (childCount > 0) {
            final View childAt = getChildAt(0);
            childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
        }

        //onLayout调用后才能通过 getWidth() getHeight() 获取值，左右坐标相减，上下坐标相减
        final int width = getWidth();
        final int height = getHeight();


    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        mPaint.setColor(Color.TRANSPARENT);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20);
        canvas.drawText("hellow world", 0, getHeight() / 2, mPaint);
    }


}
