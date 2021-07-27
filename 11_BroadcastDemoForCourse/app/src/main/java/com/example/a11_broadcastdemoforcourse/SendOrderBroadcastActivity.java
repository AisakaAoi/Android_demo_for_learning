package com.example.a11_broadcastdemoforcourse;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/21
 * </pre>
 */
public class SendOrderBroadcastActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order_broadcast);
    }

    /**
     * 发送1000w
     * @param view
     */
    public void sendOrderBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_ORDER_BROADCAST);
        intent.setComponent(new ComponentName(getPackageName(), "com.example.a11_broadcastdemoforcourse.HighLevelReceiver"));
        sendOrderedBroadcast(intent, null);
    }
}
