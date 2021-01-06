package com.javase.collection;

import java.util.ArrayList;
import java.util.Collection;

public class ContatinsTest {
    public static void main(String[] args) {
        Collection c= new ArrayList();

        c.add(new String("abc"));
        c.add(new String("def"));

        c.add(new Object());

        System.out.println(c.contains(new String("abc")));//ture
        System.out.println(c.contains(new Object()));//false
    }
}
