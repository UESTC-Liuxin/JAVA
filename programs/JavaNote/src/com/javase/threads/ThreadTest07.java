package com.javase.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadTest07 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("call method begin");
                Thread.sleep(1000*10);
                System.out.println("call method end!");

                int a =100;
                int b =200;
                return a+b;
            }
        });
        //创建线程对象
        Thread  t = new Thread(task);
        //这里是main方法，这是在主线程中
        t.start();
        //获取返回对象结果，但是当前线程会阻塞
        Object obj = task.get();
        System.out.println(obj);
    }
}
