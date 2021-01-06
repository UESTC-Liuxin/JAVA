package com.javase.collection;

import java.util.ArrayList;
import java.util.Collection;

public class RemoveTest {
    public static void main(String[] args) {
        Collection c=new ArrayList();

        String s1= new String("abc");
        String s2= new String("abc");

        c.add(s1);
        c.remove(s2);
        System.out.println(c.size());//0
    }
}
