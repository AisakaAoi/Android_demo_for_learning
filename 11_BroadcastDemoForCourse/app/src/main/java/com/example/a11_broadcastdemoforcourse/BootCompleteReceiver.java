package com.example.a11_broadcastdemoforcourse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/21
 * </pre>
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String TAG = "BootCompleteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "action is == " + action);
        Log.d(TAG, "开机完成");
        Toast.makeText(context, "开机完成...", Toast.LENGTH_SHORT).show();
    }
}
