package com.javase.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderTest01 {
    public static void main(String[] args) throws IOException {

        //字节流
        FileInputStream in = new FileInputStream("JavaNote/src/com/javase/io/write_test");
        //通过转换流转换
        InputStreamReader reader = new InputStreamReader(in);
        //这个构造方法只能传一个字符流，不能传字节流
        BufferedReader br = new BufferedReader(reader);

        System.out.println(br.readLine());
    }
}
