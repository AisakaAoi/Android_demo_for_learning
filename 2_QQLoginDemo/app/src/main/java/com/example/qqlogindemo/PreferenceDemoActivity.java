package com.example.qqlogindemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/16
 * </pre>
 */
public class PreferenceDemoActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "PreferenceDemoActivity";
    private Switch mIsAllowUnknownSource;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_demo);
        mIsAllowUnknownSource = this.findViewById(R.id.is_allow_unknown_apps_sources_switch);
        mIsAllowUnknownSource.setOnCheckedChangeListener(this);
        mSharedPreferences = this.getSharedPreferences("setting_info", MODE_PRIVATE);
        boolean state = mSharedPreferences.getBoolean("state", false);
        mIsAllowUnknownSource.setChecked(state);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(TAG, "current state == " + isChecked);
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putBoolean("state", isChecked);
        edit.commit();
    }
}
