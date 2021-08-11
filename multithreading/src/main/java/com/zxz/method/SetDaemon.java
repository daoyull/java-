package com.zxz.method;

public class SetDaemon extends Thread{
    @Override
    public void run() {
        while(true){
            System.out.println("我是守护进程------");
        }
    }
}
