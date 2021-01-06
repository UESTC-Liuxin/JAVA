package com.javase.threads;

public class ThreadTest06 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MyThread08());
        t.setName("t");
        t.setDaemon(true); //设置为守护线程
        t.start();
        for (int i=0;i<10;i++){
            Thread.sleep(1000);
        }

    }
}


class MyThread08 implements Runnable{
    @Override
    public void run() {
        int i=0;
        while (true){
            System.out.println(Thread.currentThread().getName()+"----->"+(i++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
