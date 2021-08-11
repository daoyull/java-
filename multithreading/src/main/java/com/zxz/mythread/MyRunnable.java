package com.zxz.mythread;

public class MyRunnable extends Father implements Runnable{
    @Override
    public void run() {
        for(int i = 1; i<=1000; i++){
            System.out.println( "MyRunnable --> " + i);
        }
    }
}
