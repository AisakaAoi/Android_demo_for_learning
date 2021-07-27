package com.example.a12_servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.a12_servicedemo.interfaces.ICommunication;

public class FirstService extends Service {

    private static final String TAG = FirstService.class.getName();

    private class InnerBinder extends Binder implements ICommunication {
        @Override
        public void callServiceInnerMethod() {
            sayHello();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "service onCreate()...");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "service onBind()...");
        return new InnerBinder();
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

    private void sayHello() {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

}