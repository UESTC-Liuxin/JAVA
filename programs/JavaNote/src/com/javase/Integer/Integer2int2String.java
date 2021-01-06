package com.javase.Integer;

public class Integer2int2String {
    public static void main(String[] args) {
        //String2int
        String s="123";
        int num =Integer.parseInt(s);
        //int2String
        String s1=String.valueOf(num);
        //int2Integer
        Integer numObj=num;
        //Integer2int
        int num1=numObj;
        //Integer2String
        String s2=String.valueOf(num1);
        //String2Integer
        Integer numObj1=Integer.valueOf(s2);

    }
}
