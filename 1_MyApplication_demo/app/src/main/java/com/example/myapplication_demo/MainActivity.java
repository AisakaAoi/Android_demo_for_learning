package com.example.myapplication_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private TextView tv_mod;
    private TextView tv_CE;
    private TextView tv_C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_layout);
        initView();
        initClickEvent();
    }

    private void initClickEvent() {
        // 第一种设置方式
        tv_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof TextView) {
                    String text = ((TextView) v).getText().toString();
                    Log.d(TAG, "TextView -> " + text);
                }
            }
        });
        // 第二种设置方式
        tv_CE.setOnClickListener(this);
        tv_C.setOnClickListener(this);
    }

    /*
     * 在这个方法中找到MainActivity的所有控件
     */
    private void initView() {
        tv_mod = (TextView) this.findViewById(R.id.tv_mod);
        tv_CE = (TextView) this.findViewById(R.id.tv_CE);
        tv_C = (TextView) this.findViewById(R.id.tv_C);
    }

    public void oneOnClick(View view) {
        if (view instanceof TextView) {
            String text = ((TextView) view).getText().toString();
            Log.d(TAG, "TextView -> " + text);
        }
    }

    @Override
    public void onClick(View v) {
//        if (v == tv_CE) {
//
//        } else if (v == tv_C) {
//
//        }
        int id = v.getId();
        switch (id) {
            case R.id.tv_CE:

                break;
            case R.id.tv_C:

                break;
        }
    }
}