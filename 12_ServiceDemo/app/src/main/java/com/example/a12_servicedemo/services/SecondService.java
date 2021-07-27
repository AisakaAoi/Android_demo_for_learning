package com.example.a12_servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.a12_servicedemo.interfaces.ICommunication;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/22
 * </pre>
 */
public class SecondService extends Service {

    private static final String TAG = SecondService.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "service onCreate()...");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "service onBind()...");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "service onStartCommand()...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "service onUnbind()...");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "service onDestroy()...");
    }

    private class InnerBinder extends Binder implements ICommunication {

        @Override
        public void callServiceInnerMethod() {

        }
    }
}
