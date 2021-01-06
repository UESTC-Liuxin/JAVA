package com.javase.threads;

public class ThreadTest01 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        thread1.start();

        for(int i=0;i<1000;i++)
            System.out.println("主线程"+i);
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        //编写程序，这段程序的代码属于子线程，在子栈中运行
        for(int i=0;i<1000;i++)
            System.out.println("分支线程"+i);

    }
}