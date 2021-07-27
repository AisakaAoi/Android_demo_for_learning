package com.example.a5_androidactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 实现页面跳转并把数据传递给另一个界面
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText mAccount;
    private EditText mPassword;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里面是登录按钮被点击了
                Log.d(TAG, "Login Click...");
                handlerLogin();
            }
        });
    }

    private void handlerLogin() {
        String accountText= mAccount.getText().toString().trim();
        if (TextUtils.isEmpty(accountText)) {
            Toast.makeText(this, "输入的账号为空...", Toast.LENGTH_SHORT).show();
            return;
        }
        String passwordText = mPassword.getText().toString().trim();
        if (TextUtils.isEmpty(passwordText)) {
            Toast.makeText(this, "输入的密码为空...", Toast.LENGTH_SHORT).show();
            return;
        }

        /**
         * 显式intent 创建一个intent 通过startActivity方法来跳转
         */
//        Intent intent = new Intent(this, SecondActivity.class);
//        intent.putExtra("account", accountText);
//        intent.putExtra("password", passwordText);
//        startActivity(intent);
        // 本质如下
        Intent intent = new Intent();

        String packageName = this.getPackageName();
        String targetActivityClassName = SecondActivity.class.getName();

        intent.setClassName(packageName, targetActivityClassName);

        Log.d(TAG, "packageName == " + packageName);
        Log.d(TAG, "name == " + targetActivityClassName);

        intent.putExtra("packageName", packageName);
        intent.putExtra("name", targetActivityClassName);

        intent.putExtra("account", accountText);
        intent.putExtra("password", passwordText);
        startActivity(intent);

        /**
         * 隐式intent
         */
//        Intent intent = new Intent();
//        intent.setAction("com.example.LOGIN_INFO");
////        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.putExtra("account", accountText);
//        intent.putExtra("password", passwordText);
//        startActivity(intent);
    }

    private void initView() {
        mAccount = (EditText) this.findViewById(R.id.account);
        mPassword = (EditText) this.findViewById(R.id.password);
        mLogin = (Button) this.findViewById(R.id.login);
    }
}