package com.example.a12_servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.a12_servicedemo.interfaces.ICommunication;
import com.example.a12_servicedemo.services.FirstService;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private boolean mIsServiceBinded;
    private ICommunication mICommunication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "activity onCreate()...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "activity onDestroy()...");
    }

    public void startServiceClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FirstService.class);
        startService(intent);
    }

    public void stopServiceClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FirstService.class);
        stopService(intent);
    }

    public void callServiceMethod(View view) {
        Log.d(TAG, "调用服务内部方法");
        // 不可以这样启动服务 会有context空指针异常
//        FirstService firstService = new FirstService();
//        firstService.sayHello();
        mICommunication.callServiceInnerMethod();
    }

    public void bindServiceClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FirstService.class);
        mIsServiceBinded = bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected()...");
            mICommunication = (ICommunication) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected()...");
            mICommunication = null;
        }
    };

    public void unbindServiceClick(View view) {
        if (mConnection != null && mIsServiceBinded) {
            unbindService(mConnection);
        }
    }
}