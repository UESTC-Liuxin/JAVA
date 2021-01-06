package com.javase.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        List list1=new ArrayList();
        List list2=new ArrayList();
        System.out.println(list1.size());//0

        System.out.println(list2.size());//0

        Collection c = new HashSet();
        c.add(1);
        c.add(2);

        List list3 = new ArrayList(c);
        System.out.println(list3.size());
     }
}
