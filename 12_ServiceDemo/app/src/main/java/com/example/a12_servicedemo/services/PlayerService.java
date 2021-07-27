package com.example.a12_servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.a12_servicedemo.interfaces.IPlayViewControl;
import com.example.a12_servicedemo.interfaces.IPlayerControl;
import com.example.a12_servicedemo.presenter.PlayerPresenter;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/07/22
 * </pre>
 */
public class PlayerService extends Service {

    private PlayerPresenter mPlayerPresenter;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mPlayerPresenter == null) {
            mPlayerPresenter = new PlayerPresenter();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mPlayerPresenter;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayerPresenter = null;
    }
}
