package com.zxz.method;

public class GetId extends Thread{
    @Override
    public void run() {
        System.out.println("thread name = " + Thread.currentThread().getName()
                + ", id == " + this.getId() );
    }
}
