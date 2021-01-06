package com.javase.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionTest01 {

    public static void main(String[] args)  {
        try{
            read();
        }
//        catch (FileNotFoundException e){
//
//        }
        catch (IOException e){

        }
    }
    /**
     *
     * @throws ClassNotFoundException
     */
    public static void doSome() throws ClassNotFoundException{

    }

    public static void read()  throws FileNotFoundException{

    }
}
