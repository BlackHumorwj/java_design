package cn.cash360.ui.activity.advanced.mvvm.demo1;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.location.GnssStatus;

/**
 * @time 2019/9/2 10:29
 * @desc
 */
public class MyLocationLinsener implements LifecycleObserver {

    private boolean enabled = false;
    private Lifecycle mLifecycle;


    public MyLocationLinsener(Context context, Lifecycle lifecycle, GnssStatus.Callback callback) {
        this.mLifecycle = lifecycle;

    }



    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start(){

    }



    public void enable(){
        if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)){


        }    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop(){

    }


}
