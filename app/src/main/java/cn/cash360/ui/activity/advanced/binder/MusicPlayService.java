package cn.cash360.ui.activity.advanced.binder;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * @time 2019/8/2 16:33
 * @desc
 */
public class MusicPlayService extends Binder {


    @Override
    protected boolean onTransact(int code,  Parcel data,   Parcel reply, int flags) throws RemoteException {
        return super.onTransact(code, data, reply, flags);
    }
}
