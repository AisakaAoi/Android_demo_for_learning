package com.example.a14_alipay;

interface ThirdPartPayResult {

    void onPaySuccess();
    void onPayFailed(in int errorCode, in String msg);

}