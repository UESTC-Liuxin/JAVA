package com.javase.threads;

public class ThreadTest05 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MyThread05());
        t.start();
        System.out.println("main..start...");
        t.join();
        System.out.println("main...end...");//需要run()执行完才会执行end

    }
}


class MyThread05 implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<100;i++)
            System.out.println(i);
    }
}