package com.example.a11_broadcastdemoforcourse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/21
 * </pre>
 */
public class AppStateChangeReceiver extends BroadcastReceiver {

    private static final String TAG = "AppStateChangeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            Log.d(TAG, "应用安装了 == " + intent.getData());
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            Log.d(TAG, "应用卸载了 == " + intent.getData());
        }
    }
}
