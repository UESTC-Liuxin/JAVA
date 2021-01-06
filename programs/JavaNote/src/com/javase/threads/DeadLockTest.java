package com.javase.threads;

public class DeadLockTest {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

        Thread t1 = new Thread(new MyThread07(o1,o2));
        Thread t2 = new Thread(new MyThread07(o2,o1));

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();

    }
}


class MyThread07 implements Runnable{
    private Object o1;
    private Object o2;

    public MyThread07(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized (o1){
            count();
            synchronized (o2){
                count();
            }
        }


    }

    private void count(){
        Thread current = Thread.currentThread();
        for(int i =0;i<1000;i++){
            try {
                Thread.sleep(1);
                System.out.println(current.getName()+"----->:"+i);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

