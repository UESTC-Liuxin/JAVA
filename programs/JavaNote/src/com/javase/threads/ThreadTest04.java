package com.javase.threads;

public class ThreadTest04 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Mythread04());
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }

}


class Mythread04 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"---> begin");
        try {
            Thread.sleep(1000*60);
        } catch (InterruptedException e) {
            e.printStackTrace();  //异常是在这里抛出的
        }
        System.out.println(Thread.currentThread().getName()+"---> end");
    }
}
