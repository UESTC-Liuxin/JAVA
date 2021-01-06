package com.javase.io;

import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File f1 = new File("/media/Program/JAVA/programs/log2/test");

        //判断文件是否存在
        System.out.println(f1.exists());

        //创建新文件，需要处理IO异常
        if(!f1.exists()){
//            f1.createNewFile();
            //创建为文件夹
            f1.mkdir();
            //递归创建文件夹
            f1.mkdirs();
            //获取上层路径
            System.out.println(f1.getParent()); ///media/Program/JAVA/programs/log2
        }


    }
}
