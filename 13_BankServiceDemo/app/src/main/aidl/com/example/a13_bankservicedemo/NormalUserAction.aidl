package com.example.a13_bankservicedemo;

interface NormalUserAction {
    void saveMoney(in float money);
    float getMoney();
    float loanMoney();
}