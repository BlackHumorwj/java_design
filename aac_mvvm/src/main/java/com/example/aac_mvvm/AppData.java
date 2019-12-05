package com.example.aac_mvvm;

import android.app.Application;
import android.content.Context;

/**
 * @time 2019/12/3 10:42
 * @desc
 */
public class AppData extends Application {

    public static Context sContext;


    @Override
    public void onCreate() {
        super.onCreate();
        sContext =this;
    }



    public static Context getContext(){
        return sContext;
    }



}
