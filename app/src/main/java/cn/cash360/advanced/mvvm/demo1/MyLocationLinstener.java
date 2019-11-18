package cn.cash360.advanced.mvvm.demo1;

import android.content.Context;
import android.location.GnssStatus;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @time 2019/9/2 10:29
 * @desc
 */
public class MyLocationLinstener implements LifecycleObserver {

    private boolean enabled = false;
    private Lifecycle mLifecycle;


    public MyLocationLinstener(Context context, Lifecycle lifecycle, GnssStatus.Callback callback) {
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
