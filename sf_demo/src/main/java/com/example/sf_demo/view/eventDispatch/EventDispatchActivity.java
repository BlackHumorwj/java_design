package com.example.sf_demo.view.eventDispatch;

import android.os.Bundle;
import android.view.MotionEvent;

import com.example.sf_demo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @time 2019/12/17 16:06
 * @desc 事件分发 【参考：https://www.jianshu.com/p/fc0590afb1bf】
 */
public class EventDispatchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
    }


    /**
     * @return 返回 super Event传递到ViewGroup；
     * <br/>true or false Activity 直接消耗次事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    //结束
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }




}
