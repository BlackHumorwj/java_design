package cn.cash360.ui.java.thread;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import java.util.HashMap;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/8/30 11:54
 * @desc
 */
public class ThreadActivity extends BaseActivity {


    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, ThreadActivity.class);
        return intent;
    }


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_thread;
    }


    @Override
    protected void initView() {
        super.initView();


        final int maxValue = Integer.MAX_VALUE;


        final HashMap<String, String> map = new HashMap<>();
        map.put("dd","aa");
        map.get("dd");
        map.remove("");


        final User user = User.getInstance();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                user.doSth("thread");
            }
        });
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                user.doSth("thread1");
            }
        });
        thread1.start();

    }


    public static class User {

        private static User mUser;
        private boolean isOperate;

        public static User getInstance() {
            if (mUser == null) {
                mUser = new User();
            }
            return mUser;
        }


        public void doSth(String url) {


            Log.e("xx", SystemClock.currentThreadTimeMillis() + "--" + url);

            if (isOperate) {
                try {
                    User.this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("xx", "进来了" + "--" + url);
            }
            synchronized (User.class) {

                isOperate = true;


                //            new Thread(new Runnable() {
                //
                //                @Override
                //                public void run() {
                //
                //                    SystemClock.sleep(2000);
                //
                //
                //                    Log.e("xx", "ggg");
                //
                //                    try {
                //                        User.this.notifyAll();
                //                    } catch (Exception e) {
                //                        e.printStackTrace();
                //                    }
                //
                //
                //                }
                //            }).start();

                SystemClock.sleep(2000);


                Log.e("xx", "ggg");

                try {
                    User.this.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }


    }
}
