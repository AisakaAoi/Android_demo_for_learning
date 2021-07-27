package com.example.a13_bankservicedemo.actions.interfaces;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/21
 * </pre>
 */
public interface IBankBossAction extends IBankWorkerAction {
    // 修改用户账户金额
    void ModifyUserAccountMoney(float money);
}
