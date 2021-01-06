package com.javase.exception;

public class ExceptionTest06 {
    public static void main(String[] args) {
        System.out.println(func());
    }

    public static int func(){
        int i=100;
        try{
            return i;//
        }finally {
            i++;
        }
    }
}
