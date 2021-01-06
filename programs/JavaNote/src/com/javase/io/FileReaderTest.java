package com.javase.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class FileReaderTest {
    public static void main(String[] args) {
        FileReader reader=null;
        try {
            reader = new FileReader("JavaNote/src/com/javase/io/write_test");
            char[] chars = new char[4];
            reader.read(chars);

            System.out.println(new String(chars));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
