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
public class DefaultLevelReceiver extends BroadcastReceiver {

    private static final String TAG = "DefaultLevelReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "default action is -> " + intent.getAction());
    }
}
