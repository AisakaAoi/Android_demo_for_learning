package com.example.a13_bankservicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.a13_bankservicedemo.actions.impl.NormalUserAIDLActionImpl;
import com.example.a13_bankservicedemo.actions.interfaces.INormalUserAction;

public class NormalUserActivity extends Activity {

    private static final String TAG = NormalUserActivity.class.getName();
    private NormalUserConnected mNormalUserConnected;
    private boolean mIsBind;
//    private INormalUserAction mINormalUserAction;
    private NormalUserAction mINormalUserAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user);
        doBindService();
    }

    private void doBindService() {
        Log.d(TAG, "doBindService()...");
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_NORMAL_USER);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage(this.getPackageName());
        mNormalUserConnected = new NormalUserConnected();
        mIsBind = bindService(intent, mNormalUserConnected, BIND_AUTO_CREATE);
    }

    private class NormalUserConnected implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected()..." + name);
//            mINormalUserAction = (INormalUserAction) iBinder;
            mINormalUserAction = NormalUserAction.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected()..." + name);
        }
    }

    public void saveMoneyClick(View view) throws RemoteException {
        Log.d(TAG, "saveMoneyClick()...");
        mINormalUserAction.saveMoney(10000);
    }

    public void getMoneyClick(View view) throws RemoteException {
        Log.d(TAG, "getMoneyClick()...");
        mINormalUserAction.getMoney();
    }

    public void loanMoneyClick(View view) throws RemoteException {
        Log.d(TAG, "loanMoneyClick()...");
        mINormalUserAction.loanMoney();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIsBind && mNormalUserConnected != null) {
            unbindService(mNormalUserConnected);
            Log.d(TAG, "unbind service...");
            mNormalUserConnected = null;
            mIsBind = false;
        }
    }
}