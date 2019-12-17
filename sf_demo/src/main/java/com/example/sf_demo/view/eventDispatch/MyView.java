package com.example.sf_demo.view.eventDispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @time 2019/12/17 16:13
 * @desc
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * @return super: MyView # onTouchEvent()
     * <br/>true: 消费此事件
     * <br/>false: ViewGroup # onTouchEvent() 调用上一层的 onTouchEvent()
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
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
