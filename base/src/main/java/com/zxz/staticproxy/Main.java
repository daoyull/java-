package com.zxz.staticproxy;

public class Main {
    public static void main(String[] args) {
        Buy buy = new BuyImpl();
        BuyProxy buyProxy = new BuyProxy(buy);
        buyProxy.buy("小米11");

    }
}
