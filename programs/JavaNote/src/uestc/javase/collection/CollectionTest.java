package uestc.javase.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionTest {
    public static void main(String[] args) {
        Collection c =new ArrayList();
        //测试Collection接口中的常用方法
        c.add(1200);//自动装箱（javase）,实际上是放进去了一个对象的内存地址。
        c.add(new Object());



    }
}
