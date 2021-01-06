package com.javase.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try{
            //创建文件字节输入流对象
            //文件路径："/media/Program/JAVA/programs"
            //以下都是采用了：绝对路径的方式
            //FileInputStream
            fis = new FileInputStream("/media/Program/JAVA/programs/read_write_test");

            //开始读
            int readData = fis.read();
            System.out.println(readData);//97

            readData = fis.read();
            System.out.println(readData);//98

            readData = fis.read();
            System.out.println(readData);//99

            readData = fis.read();
            System.out.println(readData);//100

            readData = fis.read();
            System.out.println(readData);//101

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
