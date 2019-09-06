package cn.cash360.advanced.handler;

import android.os.Handler;
import android.os.Message;

import com.orhanobut.logger.Logger;

/**
 * @time 2019/8/1 15:27
 * @desc
 */
public class MyHandler extends Handler {


    @Override
    public void handleMessage(Message msg) {

        Logger.i("handler",msg);

        super.handleMessage(msg);
    }
}
