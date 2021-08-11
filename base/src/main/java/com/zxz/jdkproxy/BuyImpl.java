package com.zxz.jdkproxy;

public class BuyImpl implements Buy {
    @Override
    public void buy(String kind) {
        System.out.println("要购买的东西为： " + kind);
    }
}
