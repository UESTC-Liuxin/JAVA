package com.javase.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private SimpleDateFormat sdf=null;
    public Logger(String logFile) {
        try {
            System.setOut(new PrintStream(new FileOutputStream(logFile,true)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss SSS");
    }



    public  void log(String msg) {
        Date date = new Date();
        String strTime = this.sdf.format(date);
        System.out.println(strTime+":"+msg);
    }


    public static void main(String[] args) {
        Logger logger = new Logger("log");
        logger.log("开始启动。");
    }
}



