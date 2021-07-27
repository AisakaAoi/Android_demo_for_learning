package com.example.a7_activityforresultdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PayActivity extends AppCompatActivity {

    private EditText mInputBox;
    private Button mPayBtn;
    private Button mCancelPayBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initView();
        initListener();
    }

    private void initListener() {

        mPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerPay();
            }
        });

        mCancelPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerCancel();
            }
        });

    }

    private void handlerPay() {
        String payNumber = mInputBox.getText().toString().trim();
        if (TextUtils.isEmpty(payNumber)) {
            Toast.makeText(PayActivity.this, "请输入充值金额！", Toast.LENGTH_SHORT).show();
            return;
        }

        // 进行网络访问 进行充值

        // setResult方法有两个重载的方法 一个是只有ResultCode的 另一个还有Intent
        Intent intent = new Intent();
        intent.putExtra("resultContent", "充值成功！");
        setResult(2, intent);
        finish();
    }

    private void handlerCancel() {
        Intent intent = new Intent();
        intent.putExtra("resultContent", "充值失败！");
        setResult(3, intent);
        finish();
    }

    private void initView() {
        mInputBox = (EditText) this.findViewById(R.id.pay_input_box);
        mPayBtn = (Button) this.findViewById(R.id.start_pay_btn);
        mCancelPayBtn = (Button) this.findViewById(R.id.cancel_pay_btn);
    }
}