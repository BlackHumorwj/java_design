package cn.cash360;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * @time 2019/8/1 15:06
 * @desc
 */
public class AppData extends Application {


    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                 return BuildConfig.DEBUG;
            }
        });

    }

    public static Context getContext() {
        return sContext;
    }
}
