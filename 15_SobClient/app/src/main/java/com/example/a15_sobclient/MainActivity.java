package com.example.a15_sobclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a14_alipay.ThirdPartPayAction;
import com.example.a14_alipay.ThirdPartPayResult;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private TextView mSobCountTv;
    private Button mBuySobBtn;
    private AlipayConnection mAlipayConnection;
    private boolean mIsBind;
    private ThirdPartPayAction mThirdPartPayAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 绑定支付宝的服务 现在开发中 这部分内容由支付宝SDK完成
        bindAlipayService();

        initView();
        initListener();
    }

    private void bindAlipayService() {
        Intent intent = new Intent();
        intent.setAction("com.example.a14_alipay.THIRD_PART_PAY");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.example.a14_alipay");
        mAlipayConnection = new AlipayConnection();
        mIsBind = bindService(intent, mAlipayConnection, BIND_AUTO_CREATE);
    }

    private class AlipayConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected()... -> " + service);
            mThirdPartPayAction = ThirdPartPayAction.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected()... -> " + name);
        }
    }

    private void initListener() {
        mBuySobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击充值!!");
                try {
                    if (mThirdPartPayAction != null) {
                        mThirdPartPayAction.requestPay("充值100", 100.0f, new PayCallback());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class PayCallback extends ThirdPartPayResult.Stub {

        @Override
        public void onPaySuccess() {
            // 支付成功 修改UI上的内容
            // 实际上是去修改数据库 其实支付宝是通过回调的URL地址通知我们的服务器
            Log.d(TAG, "充值成功！");
            mSobCountTv.setText("100");
            Toast.makeText(MainActivity.this, "充值成功！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPayFailed(int errorCode, String msg) {
            Log.d(TAG, "充值失败！errorCode -> " + errorCode + " msg -> " + msg);
            Toast.makeText(MainActivity.this, "充值失败！", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        mSobCountTv = (TextView) this.findViewById(R.id.sob_count_tv);
        mBuySobBtn = (Button) this.findViewById(R.id.buy_sob_btn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIsBind && mAlipayConnection != null) {
            Log.d(TAG, "unbind alipay service...");
            unbindService(mAlipayConnection);
            mAlipayConnection = null;
            mIsBind = false;
        }
    }
}