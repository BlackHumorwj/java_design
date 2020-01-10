package com.example.sf_demo.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

/**
 * @time 2019/12/17 17:21
 * @desc
 */
public class HandlerSource {


    void doAnalyze() {


        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                return false;
            }
        });
        handler.sendEmptyMessage(1);

        Looper.prepare();

    }


    void threadLocal() {


        final ThreadLocal<String> threadLocal = new ThreadLocal<>();

        threadLocal.set("main");
        threadLocal.get();

        new Thread("Thread#1") {
            @Override
            public void run() {

                threadLocal.set("Thread#1");


            }
        }.start();


    }


}
