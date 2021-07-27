package com.example.a6_componentdatadeliver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/19
 * </pre>
 */
public class SecondActivity extends Activity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        if (intent != null) {
            int intValue = intent.getIntExtra("intKey", -1);
            boolean booleanValue = intent.getBooleanExtra("booleanKey", false);
            Log.d(TAG, "intKey == " + intValue);
            Log.d(TAG, "booleanValue == " + booleanValue);

            User userValue = intent.getParcelableExtra("userKey");
            if (userValue != null) {
                Log.d(TAG, "userName == " + userValue.getName());
                Log.d(TAG, "userAge == " + userValue.getAge());
                Log.d(TAG, "userTall == " + userValue.getTall());
            }
        }
    }

}
