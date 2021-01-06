package com.javase.threads;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date firstTime = sdf.parse("19:23:00");
        //每2s执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Date currentTime = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(sdf.format(currentTime));
            }
        },firstTime, 1000 * 2);
    }

    public static void printTask(){
        System.out.println("time is ok...");
    }
}
