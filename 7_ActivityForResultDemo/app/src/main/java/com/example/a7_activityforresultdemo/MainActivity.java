package com.example.a7_activityforresultdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mReCharge;
    private TextView mPayResultText;

    private static final int PAY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        mReCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PayActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, PAY_REQUEST_CODE);
            }
        });
    }

    /**
     * 初始化View
     */
    private void initView() {
        mReCharge = (Button) this.findViewById(R.id.recharge_btn);
        mPayResultText = (TextView) this.findViewById(R.id.pay_result);
    }

    /**
     * 返回的结果会在该方法回调
     * @param requestCode   请求码 区别同个Activity发出的多个不同请求
     * @param resultCode    结果码 区别返回的结果的不同
     * @param data          intent 内含传递回的结果数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAY_REQUEST_CODE) {
            String resultContent = null;
            if (resultCode == 2) {
                // 充值成功
                // 其他处理
                resultContent = data.getStringExtra("resultContent");
            } else if (resultCode == 3) {
                // 充值失败
                resultContent = data.getStringExtra("resultContent");
            }
            mPayResultText.setText(resultContent);
        }
    }
}