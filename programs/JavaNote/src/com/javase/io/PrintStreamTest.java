package com.javase.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

public class PrintStreamTest {
    public static void main(String[] args) throws IOException {
        System.out.println("hello world");

        PrintStream ps = System.out;
        ps.println("hello world");

        PrintStream printStream = new PrintStream(new FileOutputStream("log"));
        System.setOut(printStream);
        System.out.println(new Date());

    }
}
