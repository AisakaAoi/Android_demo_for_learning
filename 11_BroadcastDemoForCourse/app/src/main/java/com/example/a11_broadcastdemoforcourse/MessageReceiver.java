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
public class MessageReceiver extends BroadcastReceiver {

    private static final String TAG = "MessageReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "action is -> " + action);
        String content = intent.getStringExtra(Constants.KEY_CONTENT);
        Log.d(TAG, "content is -> " + content);
    }
}
