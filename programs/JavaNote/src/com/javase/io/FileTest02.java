package com.javase.io;

import java.io.File;

public class FileTest02 {
    public static void main(String[] args) {
        File f2 = new File("/media/Program/JAVA/programs/JavaNote/src");
        //或者用list(获取所有文件以及文件目录)
        File[] files = f2.listFiles();
        for(File f:files){
            System.out.println(f.getName());
        }
    }
}
