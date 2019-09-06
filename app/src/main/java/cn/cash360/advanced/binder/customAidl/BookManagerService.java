package cn.cash360.advanced.binder.customAidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.cash360.advanced.binder.aidlMy.Book;

/**
 * @time 2019/8/2 16:33
 * @desc 远程服务，跨进程
 */
public class BookManagerService extends Service {




    BookManagerImpl mBookManager = new BookManagerImpl() {
        @Override
        public void addBook(Book book) throws RemoteException {
            mList.add(book);
        }


        @Override
        public List<Book> getBookList() throws RemoteException {
            return mList;
        }
    };


    CopyOnWriteArrayList<Book> mList = new CopyOnWriteArrayList<>();

    @Override
    public IBinder onBind(Intent intent) {

        Log.e("xx","onBind");

        return mBookManager;
    }


    //设置死亡代理
    IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (mBookManager == null) {
                return;
            }
            mBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mBookManager = null;

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mList.add(new Book("小米科技", "小李"));
        mList.add(new Book("小米科技2", "小李"));

        Log.e("xx",mList.size()+"");
    }
}
