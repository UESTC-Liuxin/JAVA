package com.javase.threads;

public class ThreadSafeTest01 {
    public static void main(String[] args) {

        Object obj = new Object();//用一个obj来做共享对象
        Account account = new Account(10000,obj);

        Thread t1 = new Thread(new MyThread06(account,obj));
        Thread t2 = new Thread(new MyThread06(account,obj));

        t1.start();
        t2.start();
    }
}

class MyThread06 implements Runnable{
    final private Account account;
    final private Object obj;

    public MyThread06(Account account, Object obj) {
        this.account = account;
        this.obj = obj;
    }

    @Override
    public void run() {

        System.out.println(account.withDrawal(5000,obj));
    }
}

class Account{
    private int balance;

    public Account(int balance, Object object) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int withDrawal(int money,Object obj)  {

        synchronized (obj) {
            //在这里获取当前余额
            int before = getBalance();
            try {
                //睡眠10ms模拟网络延迟
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = before - money;
            return balance;
        }
    }
}