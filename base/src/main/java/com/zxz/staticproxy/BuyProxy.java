package com.zxz.staticproxy;

public class BuyProxy implements Buy{

    private final Buy buy;

    public BuyProxy(Buy buy) {
        this.buy = buy;
    }

    @Override
    public void buy(String kind) {
        System.out.println("多快好省代购");
        buy.buy(kind);
        System.out.println("查找全平台最低价");
        System.out.println("购买！");
    }
}
