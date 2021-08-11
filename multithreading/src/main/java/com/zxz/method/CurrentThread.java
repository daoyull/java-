package com.zxz.method;

public class CurrentThread extends Thread {
    public CurrentThread() {
        System.out.println("run 方 法 打 印 当 前 线 程 名 称 :" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run 方 法 打 印 当 前 线 程 名 称 :" + Thread.currentThread().getName());
    }
}
