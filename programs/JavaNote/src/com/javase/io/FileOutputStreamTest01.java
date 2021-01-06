package com.javase.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest01 {
    public static void main(String[] args) {
        FileOutputStream fos=null;
        try{
            //没有文件会新建，但是不能递归创建文件夹，每次写入会覆盖(可将append参数设置为true)
            fos = new FileOutputStream("JavaNote/src/com/javase/io/write_test",true);
            byte[] bytes = new byte[]{97,98,99,100};
            //开始写
            fos.write(bytes);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
