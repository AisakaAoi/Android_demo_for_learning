package com.example.a5_androidactivitydemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/19
 * </pre>
 */
public class Skip2BrowserActivity extends Activity {

    private static final String TAG = "Skip2BrowserActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip2browser);
    }

    /**
     * 点击按钮后执行跳转到浏览器
     * @param view 这个是我们点击的button
     */
    public void skip2BrowserVisible(View view) {
        Log.d(TAG, "skip2Browser");
        Intent intent = new Intent();
        /**
         * 第一种写法 已知目标包名
         */
//        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        /**
         * 第二种写法 已知目标包名
         */
        ComponentName componentName = new ComponentName("com.android.browser", "com.android.browser.BrowserActivity");
        intent.setComponent(componentName);

        startActivity(intent);
    }

    /**
     * 通过隐式意图跳转到浏览器界面
     * 步骤：
     * 1. 创建Intent对象
     * 2. 给intent对象设置Action 设置它的Category值，如果5.1以上系统需要设置包名
     * 3. startActivity来跳转到另一个界面
     * @param view
     */
    public void skip2BrowserInvisible(View view) {
        Intent intent = new Intent();

        intent.setAction("android.intent.action.SEARCH");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage("com.android.browser");

        startActivity(intent);
    }
}
