package com.example.a11_broadcastdemoforcourse;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/21
 * </pre>
 */
public class SendBroadcastActivity extends Activity {

    private static final String TAG = "SendBroadcastActivity";
    private EditText mInputBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        mInputBox = (EditText) this.findViewById(R.id.be_send_msg_input_box);
    }

    public void sendBroadcastMsg(View view) {
        String content = mInputBox.getText().toString().trim();
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_SEND_MSG);
        intent.putExtra(Constants.KEY_CONTENT, content);
        // 注意：Android8.0后对静态广播的使用上做了一些限制 具体点击详情
        // 解决办法 1.使用动态广播代替静态广播 2.保留原来的静态广播但加入Component参数如下：参数一是包名，参数二是接收器的路径
        intent.setComponent(new ComponentName(getPackageName(), "com.example.a11_broadcastdemoforcourse.MessageReceiver"));
        sendBroadcast(intent);
    }

}
