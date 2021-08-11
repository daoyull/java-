package com.zxz.cglib;

public class Main {
    public static void main(String[] args) {
        Buy proxy = (Buy) CglibProxyFactory.getProxy(Buy.class);
        proxy.buy("小米11");
    }
}
