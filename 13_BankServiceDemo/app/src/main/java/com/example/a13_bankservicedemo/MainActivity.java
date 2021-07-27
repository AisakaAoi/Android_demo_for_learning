package com.example.a13_bankservicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void normalUserClick(View view) {
        startActivity(new Intent(this, NormalUserActivity.class));
    }

    public void bankWorkerClick(View view) {
        startActivity(new Intent(this, BankWorkerActivity.class));
    }

    public void bankBossClick(View view) {
        startActivity(new Intent(this,BankBossActivity.class));
    }

}