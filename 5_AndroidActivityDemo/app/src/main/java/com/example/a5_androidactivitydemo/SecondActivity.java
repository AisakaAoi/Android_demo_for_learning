package com.example.a5_androidactivitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/19
 * </pre>
 */
public class SecondActivity extends Activity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
