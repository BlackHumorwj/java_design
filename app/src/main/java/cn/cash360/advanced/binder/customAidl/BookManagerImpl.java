package cn.cash360.advanced.binder.customAidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;

import cn.cash360.advanced.binder.aidlMy.Book;

/**
 * @time 2019/9/6 15:35
 * @desc
 */
public class BookManagerImpl extends Binder implements IBookManager {


    /**
     * Binder的唯一标识 ，一般当前的类别标识
     */
    private static final java.lang.String DESCRIPTOR = "cn.cash360.advanced.binder.customAidl.IBookManager";


    public BookManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }


    /**
     * 将服务端的Binder对象转换成客户端所需要的AIDL接口类型的对象
     *
     * @param obj 服务端的 IBinder 对象
     * @return 客户端所需要的接口对象
     */
    public static IBookManager asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);

        //客户端和服务端在同一个进程中无需转换，直接将服务端的Binder强转为接口类型对象
        if (((iin != null) && (iin instanceof cn.cash360.advanced.binder.aidlMy.BookManager))) {
            return ((BookManagerImpl) iin);
        }

        //通过 Proxy 将 服务端的Binder对象转为 客户端AIDL接口对象
        return new BookManagerImpl.Proxy(obj);
    }


    @Override
    public List<Book> getBookList() throws RemoteException {
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {

    }

    /**
     * 返回当前的Binder对象
     *
     * @return
     */
    @Override
    public IBinder asBinder() {
        return this;
    }

    /**
     * 运行在服务端Binder线程池中，当客户端发起跨进程请求时，远程请求会通过系统底层封装交给这个方法处理
     *
     * @param code  客户端调用方法的标识
     * @param data  客户端传递过来的数据封装类
     * @param reply 返回给客户端的数据封装类
     * @param flags
     * @return 返回 false 客户端请求失败，可以做权限控制
     * @throws android.os.RemoteException
     */
    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        java.lang.String descriptor = DESCRIPTOR;
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(descriptor);
                return true;
            }
            case TRANSACTION_addBook: {
                data.enforceInterface(descriptor);
                cn.cash360.advanced.binder.aidlMy.Book _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = cn.cash360.advanced.binder.aidlMy.Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.addBook(_arg0);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_getList: {
                data.enforceInterface(descriptor);
                java.util.List<cn.cash360.advanced.binder.aidlMy.Book> _result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            }
            default: {
                return super.onTransact(code, data, reply, flags);
            }
        }
    }


    private static class Proxy implements IBookManager {

        private android.os.IBinder mRemote;

        Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        /**
         * 这个方法运行在客户端，当客户端远程调用此方法时，
         *
         * @return
         * @throws android.os.RemoteException
         */
        @Override
        public java.util.List<cn.cash360.advanced.binder.aidlMy.Book> getBookList() throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            java.util.List<cn.cash360.advanced.binder.aidlMy.Book> _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);

                //发起远程过程调用（RPC）请求，同时当前线程挂起。然后服务端的onTransact()方法被调用，直到RPC过程返回，当前线程继续执行
                //并从_reply中取出RPC过程的返回结果，最后返回_reply中的数据
                mRemote.transact(TRANSACTION_getList, _data, _reply, 0);

                _reply.readException();
                _result = _reply.createTypedArrayList(cn.cash360.advanced.binder.aidlMy.Book.CREATOR);

            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }

        @Override
        public void addBook(cn.cash360.advanced.binder.aidlMy.Book book) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                if ((book != null)) {
                    _data.writeInt(1);
                    book.writeToParcel(_data, 0);
                } else {
                    _data.writeInt(0);
                }
                mRemote.transact(TRANSACTION_addBook, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }


    }

}
