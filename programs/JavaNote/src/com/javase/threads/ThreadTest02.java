package com.javase.threads;

public class ThreadTest02 {
    public static void main(String[] args) {
        Thread t= new Thread(new MyThread02());
        t.start();
        for(int i=0;i<1000;i++)
            System.out.println("主线程"+i);
    }
}


class MyThread02 implements Runnable{
    @Override
    public void run() {
        //编写程序，这段程序的代码属于子线程，在子栈中运行
        for(int i=0;i<1000;i++)
            System.out.println("分支线程"+i);
    }

}