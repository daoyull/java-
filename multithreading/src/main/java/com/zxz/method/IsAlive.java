package com.zxz.method;

public class IsAlive extends Thread{
    @Override
    public void run() {
        System.out.println("run 方法, isAlive = " + this.isAlive()); //运行状态,true
    }
}
