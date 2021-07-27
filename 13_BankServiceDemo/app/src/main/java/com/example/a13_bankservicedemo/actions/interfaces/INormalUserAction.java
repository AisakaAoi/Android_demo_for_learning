package com.example.a13_bankservicedemo.actions.interfaces;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/21
 * </pre>
 */
public interface INormalUserAction {
    // 存钱
    void saveMoney(float money);
    // 取钱
    float getMoney();
    // 贷款
    float loanMoney();
}
