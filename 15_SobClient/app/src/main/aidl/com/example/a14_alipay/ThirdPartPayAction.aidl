package com.example.a14_alipay;

import com.example.a14_alipay.ThirdPartPayResult;

interface ThirdPartPayAction {

    void requestPay(String orderInfo, float payMoney, ThirdPartPayResult callback);

}