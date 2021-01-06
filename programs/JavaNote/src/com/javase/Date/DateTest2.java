package com.javase.Date;
/*
* 获取自1970年1月1日 00:00:00 000到当前系统时间的总毫秒数
* */


public class DateTest2 {
    public static void main(String[] args) throws InterruptedException {
        //获取自1970年1月1日 00:00:00 000到当前系统时间的总毫秒数
        long preTimeMillis=System.currentTimeMillis();
        //System.out.println(preTimeMillis);//1608088051645

        //统计一个方法耗时
        //在调用目标方法之前记录一个毫秒数
        Thread.sleep(100);//睡眠100 ms
        long endTimeMillis=System.currentTimeMillis();
        System.out.println(endTimeMillis-preTimeMillis);

    }
}
