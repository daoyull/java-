package com.zxz.method;

public class SetPriority extends Thread{
    @Override
    public synchronized void start() {
        long begin = System.currentTimeMillis();
        long sum = 0 ;
        for(long i = 0 ; i<= 10000000000L; i++){
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("time : " + (end - begin));
    }
}
