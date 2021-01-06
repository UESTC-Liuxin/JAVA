package com.javase.inner;

public class StaticInnerClassTest {

    public static void main(String[] args) {
        Student.School.name="nankai";

    }
}

class Student{
    public static class School{
        public static String name;
        public static String address;
    }

}

