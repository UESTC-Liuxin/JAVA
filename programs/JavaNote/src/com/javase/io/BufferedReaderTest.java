package com.javase.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {

        //定义节点流
        FileReader reader = new FileReader("JavaNote/src/com/javase/io/write_test");

        //定义包装流
        BufferedReader br = new BufferedReader(reader);

        String s=null;
        while ((s= br.readLine())!= null){
            System.out.println(s);
        }

        //关闭流
        br.close();
    }
}
