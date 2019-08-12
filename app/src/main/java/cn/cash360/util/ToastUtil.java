package cn.cash360.util;

import android.text.TextUtils;
import android.widget.Toast;

import cn.cash360.AppData;


/**
 * Created by DragonBall on 2015/12/1.
 */
public class ToastUtil {
    public static void toast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        //   Toast.makeText(BaseApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(AppData.getContext(), msg, Toast.LENGTH_LONG).show();
    }





}
