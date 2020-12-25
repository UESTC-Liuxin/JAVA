package uestc.javase.exception;

import java.io.FileNotFoundException;

public class ExceptionTest03 {
    public static void main(String[] args) {
        try{
            doSome();
        }
        catch (ClassNotFoundException | FileNotFoundException e){

        }
    }

    public static void doSome() throws ClassNotFoundException , FileNotFoundException{

    }
}
