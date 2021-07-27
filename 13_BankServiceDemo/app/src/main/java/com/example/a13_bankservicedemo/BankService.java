package com.example.a13_bankservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.a13_bankservicedemo.actions.impl.BankBossActionImpl;
import com.example.a13_bankservicedemo.actions.impl.BankWorkerActionImpl;
import com.example.a13_bankservicedemo.actions.impl.NormalUserAIDLActionImpl;
import com.example.a13_bankservicedemo.actions.impl.NormalUserActionImpl;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/21
 * </pre>
 */
public class BankService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            if (Constants.ACTION_NORMAL_USER.equals(action)) {
//                return new NormalUserActionImpl();
                return new NormalUserAIDLActionImpl();
            } else if (Constants.ACTION_BANK_WORKER.equals(action)) {
                return new BankWorkerActionImpl();
            } else if (Constants.ACTION_BANK_BOSS.equals(action)) {
                return new BankBossActionImpl();
            }
        }
        return null;
    }

}
