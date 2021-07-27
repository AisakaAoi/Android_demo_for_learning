package com.example.a10_activitylaunchmodedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void openFirst(View view) {
        startActivity(new Intent(this, FirstActivity.class));
    }

    public void openSecond(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}