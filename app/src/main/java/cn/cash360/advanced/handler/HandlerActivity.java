package cn.cash360.advanced.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/8/1 14:45
 * @desc
 */
public class HandlerActivity extends BaseActivity {




    MyHandler mHandler = new MyHandler();



    public static Intent newInstance(Context context) {
       Intent intent = new Intent(context,HandlerActivity.class);
        return intent;
    }


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_handler;
    }

    @Override
    protected void initData() {
        super.initData();
        new Thread(new Runnable() {
            @Override
            public void run() {


                Log.e("ddddd",Thread.currentThread().getName());

                //ThreadLocal 线程局部存储 线程间数据隔离
                //1、准备当前线程的 Looper 和 MessageQueue
                Looper.prepare();


                final Handler handler = new MyHandler();


                handler.sendEmptyMessage(1001);

                final Handler handler1 = new MyHandler();


                handler1.sendEmptyMessage(1002);
                mHandler.sendEmptyMessage(1002);


                //开启循环，变遍历MessageQueue 处理消息,处理的回调 会调用对于的Handler 的 handleMessage()方法
                Looper.loop();

            }
        }).start();
    }
}
