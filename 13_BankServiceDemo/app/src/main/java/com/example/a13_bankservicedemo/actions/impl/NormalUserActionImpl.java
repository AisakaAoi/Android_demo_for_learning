package com.example.a13_bankservicedemo.actions.impl;

import android.os.Binder;
import android.util.Log;

import com.example.a13_bankservicedemo.actions.interfaces.INormalUserAction;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/21
 * </pre>
 */
public class NormalUserActionImpl extends Binder implements INormalUserAction {

    private static final String TAG = NormalUserActionImpl.class.getName();

    @Override
    public void saveMoney(float money) {
        Log.d(TAG, "saveMoney -> " + money);
    }

    @Override
    public float getMoney() {
        Log.d(TAG, "getMoney -> 100.00rmb");
        return 100.0f;
    }

    @Override
    public float loanMoney() {
        Log.d(TAG, "loanMoney -> 100.00rmb");
        return 100.0f;
    }

}
