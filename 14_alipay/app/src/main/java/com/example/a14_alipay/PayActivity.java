package com.example.a14_alipay;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/22
 * </pre>
 */
public class PayActivity extends Activity {

    private static final String TAG = PayActivity.class.getName();
    private boolean mIsBind;
    private EditText mPasswordBox;
    private PayService.PayAction mPayAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        // Activity要跟服务进行通讯告诉服务支付结果 所以也要绑定服务
        doBindService();
        initView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mPayAction != null) {
            mPayAction.onUserCancel();
        }
    }

    private void initView() {
        Intent intent = getIntent();
        String orderInfo = intent.getStringExtra(Constants.KEY_BILL_INFO);
        float payMoney = intent.getFloatExtra(Constants.KEY_PAY_MONEY, 0f);
        TextView orderInfoTv = this.findViewById(R.id.order_info_tv);
        orderInfoTv.setText("支付信息：" + orderInfo);
        TextView payMoneyTv = this.findViewById(R.id.pay_money);
        payMoneyTv.setText("支付金额：" + payMoney + "元");
        mPasswordBox = this.findViewById(R.id.pay_password_input);
        Button commitBtn = this.findViewById(R.id.pay_commit);
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = mPasswordBox.getText().toString().trim();
                if ("123456".equals(password) && mPayAction != null) {
                    mPayAction.Pay(payMoney);
                    Toast.makeText(PayActivity.this, "支付成功！", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "pay finish...");
                    finish();
                } else {
                    Toast.makeText(PayActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void doBindService() {
        Intent intent = new Intent(this, PayService.class);
        mIsBind = bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            mPayAction = (PayService.PayAction) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mPayAction = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIsBind && mConnection != null) {
            unbindService(mConnection);
            mConnection = null;
            mIsBind = false;
        }
    }
}
