package com.example.a6_componentdatadeliver;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/20
 * </pre>
 */
public class sendMsgActivity extends Activity {

    private static final String TAG = "sendMsgActivity";
    private EditText mReceiverPhoneNumberEt;
    private EditText mContentEt;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msm);

        initView();

        Intent intent = getIntent();
        if (intent != null) {
            String targetNumValue = intent.getStringExtra("targetNumKey");
            Log.d(TAG, "number == " + targetNumValue);
            mReceiverPhoneNumberEt.setText(targetNumValue);
            Uri data = intent.getData();
            Log.d(TAG, "data id == " + data);
            if (data != null) {
                String content = data.toString().replace("msg:", "");
                mContentEt.setText(content);
            }
        }

    }

    private void initView() {
        mReceiverPhoneNumberEt = (EditText) this.findViewById(R.id.receiver_phone_number_et);
        mContentEt = (EditText) this.findViewById(R.id.msg_content_et);
        mButton = (Button) this.findViewById(R.id.sent_msg_btn);
    }

}
