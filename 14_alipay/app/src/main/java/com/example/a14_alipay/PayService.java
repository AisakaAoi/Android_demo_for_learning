package com.example.a14_alipay;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/22
 * </pre>
 */
public class PayService extends Service {

    private static final String TAG = PayService.class.getName();
    private ThirdPartPayImpl mThirdPartPay;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onBind() action -> " + action);
        if (action != null && "com.example.a14_alipay.THIRD_PART_PAY".equals(action)) {
            mThirdPartPay = new ThirdPartPayImpl();
            return mThirdPartPay;
        }
        return new PayAction();
    }

    public class PayAction extends Binder {
        public void Pay(float payMoney) {
            Log.d(TAG, "pay money is -> " + payMoney);
            // 支付的方法
            if (mThirdPartPay != null) {
                try {
                    mThirdPartPay.paySuccess();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        public void onUserCancel() {
            // 用户点击界面上的取消/退出
            try {
                mThirdPartPay.payFailed(1, "user cancel pay");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private class ThirdPartPayImpl extends ThirdPartPayAction.Stub {

        private ThirdPartPayResult mCallback;

        @Override
        public void requestPay(String orderInfo, float payMoney, ThirdPartPayResult callback) {
            this.mCallback = callback;
            // 第三方应用发起请求 打开一个支付界面
            Intent intent = new Intent();
            intent.setClass(PayService.this, PayActivity.class);
            intent.putExtra(Constants.KEY_BILL_INFO, orderInfo);
            intent.putExtra(Constants.KEY_PAY_MONEY, payMoney);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        public void paySuccess() throws RemoteException {
            if (mCallback != null) {
                mCallback.onPaySuccess();
            }
        }

        public void payFailed(int errorCode, String errorMsg) throws RemoteException {
            if (mCallback != null) {
                mCallback.onPayFailed(errorCode, errorMsg);
            }
        }

    }
}
