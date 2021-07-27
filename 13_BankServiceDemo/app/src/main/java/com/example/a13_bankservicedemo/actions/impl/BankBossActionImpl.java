package com.example.a13_bankservicedemo.actions.impl;

import android.os.Binder;
import android.util.Log;

import com.example.a13_bankservicedemo.actions.interfaces.IBankBossAction;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/21
 * </pre>
 */
public class BankBossActionImpl extends Binder implements IBankBossAction {

    private static final String TAG = BankBossActionImpl.class.getName();

    @Override
    public void ModifyUserAccountMoney(float money) {
        Log.d(TAG, "ModifyUserAccountMoney -> 1000000");
    }

    @Override
    public void checkUserCredit() {
        Log.d(TAG, "checkUserCredit -> 790");
    }

    @Override
    public void freezeUserAccount() {
        Log.d(TAG, "freezeUserAccount -> 0");
    }

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
