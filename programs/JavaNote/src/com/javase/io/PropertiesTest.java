package com.javase.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        /**
         * Properties是一个Map集合，key和value都是String类型。
         * 想将userinfo文件中的数据加载到Propertise对象当中。
         */
        //新建一个输入流对象
        FileInputStream fis = new FileInputStream("./JavaNote/userinfo");
        Properties pro = new Properties();
        pro.load(fis);
        fis.close();
        System.out.println(pro);//{password=123456, user=liuxin}
    }
}
