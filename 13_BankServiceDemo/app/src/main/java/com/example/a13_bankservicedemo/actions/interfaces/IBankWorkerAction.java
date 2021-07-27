package com.example.a13_bankservicedemo.actions.interfaces;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/21
 * </pre>
 */
public interface IBankWorkerAction extends INormalUserAction {
    // 查用户的信用
    void checkUserCredit();
    // 冻结用户账号
    void freezeUserAccount();
}
