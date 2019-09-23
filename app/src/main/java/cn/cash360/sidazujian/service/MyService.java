package cn.cash360.sidazujian.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @time 2019/9/9 10:51
 * @desc
 */
public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
