package com.example.a5_androidactivitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/19
 * </pre>
 */
public class ThirdActivity extends Activity {

    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView info = this.findViewById(R.id.info);

        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        String password = intent.getStringExtra("password");

        Log.d(TAG, "account == " + account);
        Log.d(TAG, "password == " + password);

        info.setText("账号：" + account + "，密码：" + password);
    }
}
