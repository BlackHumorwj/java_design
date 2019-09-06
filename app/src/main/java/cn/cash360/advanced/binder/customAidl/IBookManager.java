package cn.cash360.advanced.binder.customAidl;

import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

import cn.cash360.advanced.binder.aidlMy.Book;

/**
 * @time 2019/9/6 15:27
 * @desc
 */
public interface IBookManager extends IInterface {


    //方法唯一标识
    //接口中定义的方法唯一id，区别客户端调用的是哪个方法
    int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION);
    int TRANSACTION_getList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

    //接口方法
    List<Book> getBookList() throws RemoteException;

    void addBook(Book book) throws RemoteException;

}
