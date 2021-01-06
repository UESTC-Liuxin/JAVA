package com.javase.threads;


import java.util.ArrayList;
import java.util.List;

/*
1.
 */
public class ThreadTest08 {
    public static void main(String[] args) {
        List list = new ArrayList();


        Thread t1 = new Thread(new Producer(list));
        Thread t2 = new Thread(new Consumer(list));

        t1.setName("Producer");
        t2.setName("Consumer");
        t1.start();
        t2.start();



    }
}

//生产者线程
class Producer implements Runnable{
    private List list;

    public Producer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        while(true) {
            //当前线程拿到对象锁
            synchronized (list) {
                if (list.size() > 0) {
                    try {
                        list.wait();//释放对象锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //程序能够执行到这里，说明仓库是空的，可以生产
                Object obj = new Object();
                list.add(obj);
                System.out.println(Thread.currentThread().getName() + "---->" + obj);
                //唤醒消费者进行消费
                list.notifyAll();
            }
        }
    }
}


//消费者线程
class Consumer implements Runnable{
    private List list;

    public Consumer(List list) {
        this.list = list;
    }

    @Override
    public void run() {

        while(true) {
            synchronized (list) {
                if (list.size() == 0) {
                    try {
                        //仓库已经空了
                        //消费者线程等待，释放掉list集合的锁
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //程序能够执行到此处说明仓库中有数据，进行消费
                Object obj = list.remove(0);
                System.out.println(Thread.currentThread().getName() + "------>" + obj);
                //唤醒生产者生产
                list.notifyAll();
            }
        }
    }
}