package com.example.a6_componentdatadeliver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 这个方法会跳转到第二个界面
     * @param view
     */
    public void skip2Second(View view) {
        // 创建意图对象
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("intKey", 100);
        intent.putExtra("booleanKey", true);
        // ...
        // 除了传单个值 还可以传同一类型的数组数据
        startActivity(intent);
    }

    /**
     * 把一个对象传递给第二个界面
     * @param view
     */
    public void deliverObject(View view) {
        /**
         * 和前面一样 先实现页面跳转
         * 1. 创建对象 所创建的对象要实现Parcelable接口
         * 2. 将对象以putExtra方式扔进去 并指定Key
         * 3. 在第二个界面通过Key获取该对象
         */
        Intent intent = new Intent(this, SecondActivity.class);

        User user = new User();
        user.setName("Aisaka");
        user.setTall(180);
        user.setAge(22);

        // intent.putExtra("stringKey", "String Value");
        // Bitmap bitmap = null;
        // intent.putExtra("birMapKey", bitmap);

        intent.putExtra("userKey", user);

        startActivity(intent);
    }

    public void call(View view) {
//        Intent intent = new Intent();

//        intent.setAction("android.intent.action.CALL");
//        intent.setAction(intent.ACTION_CALL);
//        intent.addCategory("android.intent.category.DEFAULT");

//        Uri uri = Uri.parse("tel:10086");
//        intent.setData(uri);

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));

        startActivity(intent);
    }

    public void sendMsg(View view) {
        Intent intent = new Intent();

        intent.setAction("com.example.componentdatadeliver.SEND.MSG");
        intent.addCategory("android.intent.category.DEFAULT");

        intent.putExtra("targetNumKey", "10086");
        intent.setData(Uri.parse("msg:查询电话费"));

        startActivity(intent);
    }
}