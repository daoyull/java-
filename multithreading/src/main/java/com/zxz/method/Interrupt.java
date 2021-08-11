package com.zxz.method;

public class Interrupt extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10000; i++) {

            if (this.isInterrupted()) {
                System.out.println("当前线程的中断标志为 true, 我要退出了");
                return; //直接结束当前 run()方法的执行
            }
            System.out.println("interrupt --> " + i);
        }
    }
}
