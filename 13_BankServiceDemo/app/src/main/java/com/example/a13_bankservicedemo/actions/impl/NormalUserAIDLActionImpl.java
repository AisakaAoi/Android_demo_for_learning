package com.example.a13_bankservicedemo.actions.impl;

import android.os.RemoteException;
import android.util.Log;

import com.example.a13_bankservicedemo.NormalUserAction;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/22
 * </pre>
 */
public class NormalUserAIDLActionImpl extends NormalUserAction.Stub {

    private static final String TAG = NormalUserAIDLActionImpl.class.getName();

    @Override
    public void saveMoney(float money) {
        Log.d(TAG, "saveMoney()..." + money);
    }

    @Override
    public float getMoney() {
        return 1000.0f;
    }

    @Override
    public float loanMoney() throws RemoteException {
        return 1000.0f;
    }


}
