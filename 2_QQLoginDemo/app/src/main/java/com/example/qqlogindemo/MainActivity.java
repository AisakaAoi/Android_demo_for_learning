package com.example.qqlogindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

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

    @Override
    protected void onResume() {
        super.onResume();
//        File filesDir = this.getFilesDir();
//        File saveFile = new File(filesDir, "info.text");
        try {
            FileInputStream fileInputStream = this.openFileInput("info.text");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String info = bufferedReader.readLine();
            // fos.write((accountText + "***" + passwordText).getBytes()); 文件保存规则
            String[] splits = info.split("\\*\\*\\*");
            String account = splits[0];
            String password = splits[1];
            // 回显数据
            mAccount.setText(account);
            mPassword.setText(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 设置事件监听
     */
    private void initListener() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerLoginEvent(v);
            }
        });
    }

    /*
     * 处理登录事件
     */
    private void handlerLoginEvent(View v) {
        // 3. 拿到界面上的内容 账号和密码
        String accountText = mAccount.getText().toString();
        String passwordText = mPassword.getText().toString();

        // 对账号密码进行数据校验 此次只判空
//        if (accountText.length() == 0) {
//            Toast.makeText(this, "账号不可以为空...", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (passwordText.length() == 0) {
//            Toast.makeText(this, "密码不可以为空...", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if (TextUtils.isEmpty(accountText)) {
            Toast.makeText(this, "账号不可以为空...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passwordText)) {
            Toast.makeText(this, "密码不可以为空...", Toast.LENGTH_SHORT).show();
            return;
        }

        // 把账号密码保存起来
        saveUserInfo(accountText, passwordText);
    }

    private void saveUserInfo(String accountText, String passwordText) {
        Log.d(TAG, "保存用户信息...");

        /*
         * 缓存文件路径 保存缓存文件 由系统根据存储情况进行清理
         * Cache Dir == /data/user/0/com.example.qqlogindemo/cache
         * 保存文件路径 可用代码删除文件 也可通过设置里的应用程序选项清除
         * files dir == /data/user/0/com.example.qqlogindemo/files
         */

        // 获取到缓存文件的路径
        File cacheDir = this.getCacheDir();
        Log.d(TAG, "Cache Dir == " + cacheDir.toString());

        // getFileDir()获取到的是/data/data/包名/files
        File filesDir = this.getFilesDir();
        File saveFile = new File(filesDir, "info.text");
        Log.d(TAG, "files dir == " + filesDir.toString());
        try {
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(saveFile);
            // 使用 账号***密码 来储存
            fos.write((accountText + "***" + passwordText).getBytes());
            fos.close();
            Toast.makeText(this, "数据保存成功...", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 初始化控件
     */
    private void initView() {
        mAccount = (EditText) this.findViewById(R.id.et_account);
        mPassword = (EditText) this.findViewById(R.id.et_password);
        mLogin = (Button) this.findViewById(R.id.bt_login);
    }
}