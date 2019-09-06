package cn.cash360.advanced.binder.customAidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;

import cn.cash360.advanced.binder.aidlMy.Book;
import cn.cash360.java_design.R;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/9/6 15:50
 * @desc
 */
public class BookManagerActivity extends BaseActivity {


    private IBookManager mIBookManager;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, BookManagerActivity.class);
        return intent;
    }


    @Override
    protected int getLayoutResID() {
        return R.layout.binder_acitivty_book_manger;
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mIBookManager = BookManagerImpl.asInterface(service);

            try {
                final List<Book> bookList = mIBookManager.getBookList();

                Log.e("xx",bookList.size() + "");

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("xx",name.getClassName());
        }
    };


    @Override
    protected void initEvent() {
        super.initEvent();


        final Intent intent = new Intent(this, BookManagerService.class);
        //绑定服务
        final boolean bindService = bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

        Log.e("xx","xxx"+"--"+bindService);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);

    }
}

