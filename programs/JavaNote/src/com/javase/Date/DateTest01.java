package com.javase.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest01 {
    public static void main(String[] args) throws ParseException {
        Date nowTime =new Date();
        System.out.println(nowTime);//Wed Dec 16 10:53:36 CST 2020

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss SSS");
        String nowTimeStr= sdf.format(nowTime);
        System.out.println(nowTimeStr);//2020-12-16 10:12:36 672

        String time="2020-12-16 0:0:0 000";
        Date dateTime = sdf.parse(time);
        System.out.println(dateTime);//Mon Dec 16 00:00:00 CST 2019 没有毫秒，因为toString没有实现，实际上次存在

    }
}
