package com.example.a11_broadcastdemoforcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mBatteryLevelText;
    private BatteryLevelReceiver mBatteryLevelReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerBatteryReceiver();
    }

    private void initView() {
        mBatteryLevelText = (TextView) this.findViewById(R.id.battery_level);
    }

    private void registerBatteryReceiver() {
        // 收听的频道是 电量变化
        IntentFilter intentFilter = new IntentFilter();
        // 设置频道
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        // 创建一个广播接收者
        mBatteryLevelReceiver = new BatteryLevelReceiver();
        // 注册
        this.registerReceiver(mBatteryLevelReceiver, intentFilter);
    }

    /**
     * 创建一个广播接收者
     */
    private class BatteryLevelReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                Log.d(TAG, "Battery change action is == " + action);
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                Log.d(TAG, "当前电量：" + currentLevel);
                if (mBatteryLevelText != null) {
                    mBatteryLevelText.setText("当前电量：" + currentLevel);
                }

                int maxLevel = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                float percent = currentLevel * 1.0f / maxLevel * 100;
                Log.d(TAG, "当前电量百分比为：" + percent + "%");
            } else if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
                Log.d(TAG, "USB已连接...");
            } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
                Log.d(TAG, "USB已断开...");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消广播注册
        if (mBatteryLevelReceiver != null) {
            this.unregisterReceiver(mBatteryLevelReceiver);
        }
    }
}