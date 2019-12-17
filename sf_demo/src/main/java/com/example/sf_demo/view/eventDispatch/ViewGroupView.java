package com.example.sf_demo.view.eventDispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @time 2019/12/17 16:10
 * @desc
 */
public class ViewGroupView extends ViewGroup {
    public ViewGroupView(Context context) {
        super(context);
    }

    public ViewGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    /**
     * @return super: ViewGroupView # onInterceptTouchEvent()
     * <br/>true: 消费此事件
     * <br/>false: Activity#onTouchEvent() 调用上一层的 onTouchEvent()
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @return true: ViewGroupView # onTouchEvent()
     * <br/> false or super: 传递到子View的 MyView # dispatchTouchEvent() 方法中
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * @return true: 消耗此事件
     * <br/> false or super: 返回给上一级的 # onTouchEvent() 方法中
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


}
