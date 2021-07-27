package com.example.a12_servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.a12_servicedemo.interfaces.ICommunication;
import com.example.a12_servicedemo.services.FirstService;
import com.example.a12_servicedemo.services.SecondService;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/22
 * </pre>
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void startServiceClick(View view) {
        Intent intent = new Intent(this, SecondService.class);
        startService(intent);
    }

    public void stopServiceClick(View view) {
        Intent intent = new Intent(this, SecondService.class);
        startService(intent);
    }

    public void callServiceMethod(View view) {

    }

    public void bindServiceClick(View view) {
        Intent intent = new Intent(this, SecondService.class);
    }

    public void unbindServiceClick(View view) {

    }

}
