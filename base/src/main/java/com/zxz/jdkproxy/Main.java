package com.zxz.jdkproxy;

public class Main {
    public static void main(String[] args) {
        Buy proxy = (Buy) JdkProxyFactory.getProxy(new BuyImpl());
        proxy.buy("小米11");
    }
}
