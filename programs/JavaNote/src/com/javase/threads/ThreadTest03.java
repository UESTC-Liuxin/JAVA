package com.javase.threads;

public class ThreadTest03 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread03());
        Thread t2 = new Thread(new MyThread03());
        //设置线程名字
        t1.setName("t1");
        t1.start();

        try{
            t1.sleep(10);  //并不会t1线程进行休眠
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        t2.setName("t2");
        t2.start();
    }
}

class MyThread03 implements Runnable{

    @Override
    public void run() {
        try {
            for (int i=0;i<100;i++) {
                Thread.sleep(10);

                //取得当前线程，并获取名字
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName()+":"+i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
