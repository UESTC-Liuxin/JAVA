package com.javase.array;

public class ArrayTest04 {
    public static void main(String[] args) {

        Object[] objects={new Object(),new Object(),new Object()};
        Object[] newObjects=new Object[objects.length];
        System.arraycopy(objects,0,newObjects,0,objects.length);
        objects.clone() ;



    }
}


