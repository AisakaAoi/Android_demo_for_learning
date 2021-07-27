package com.example.a9_activitylifecircledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String MSG_RECORD = "msg_record";
    private static final String RECORD_KEY = "msg";

    private EditText mContentBox;
    private Button mSendBtn;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        mSharedPreferences = this.getSharedPreferences(MSG_RECORD, MODE_PRIVATE);
        String record = mSharedPreferences.getString(RECORD_KEY, null);
        if (!TextUtils.isEmpty(record)) {
            mContentBox.setText(record);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String content = mContentBox.getText().toString().trim();
        if (!TextUtils.isEmpty(content)) {
            SharedPreferences.Editor edit = mSharedPreferences.edit();
            edit.putString(RECORD_KEY, content);
            edit.commit();
        }
    }

    private void initListener() {
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取到短信内容
                String content = mContentBox.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(MainActivity.this, "请输入要发送的内容...", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, "发送短信...");
            }
        });
    }

    private void initView() {
        mContentBox = (EditText) this.findViewById(R.id.content);
        mSendBtn = (Button) this.findViewById(R.id.send);
    }
}