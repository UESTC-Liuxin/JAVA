package com.javase.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest01 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try{
            //创建文件字节输入流对象
            //文件路径："/media/Program/JAVA/programs"
            //以下都是采用了：绝对路径的方式
            //FileInputStream

            //工程目录在Programs
            fis = new FileInputStream("JavaNote/src/com/javase/io/read_write_test");

            //开始读，采用byte数组，一次读取多个字节。最多读取“数组.length个字节”
            byte[] bytes = new byte[8];
            int readCount = fis.read(bytes);
            System.out.println(readCount);
            System.out.println(new String(bytes,0,readCount));//abcdefgh

            readCount = fis.read(bytes);
            System.out.println(readCount);
            System.out.println(new String(bytes,0,readCount));//ijkdefgh

            readCount = fis.read(bytes);
            System.out.println(readCount);
            if(readCount>0)
                System.out.println(new String(bytes,0,readCount));//ijkdefgh


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
