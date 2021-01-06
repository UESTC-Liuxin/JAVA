package com.javase.equalsTest;

import java.util.Objects;

public class EqualsTest {


    public static void main(String[] args) {
        MyTime t1 = new MyTime(2008, 1, 1);
        MyTime t2 = new MyTime(2008, 1, 1);
        System.out.println(t1.equals(t2));

        String s1 = "aaa";
        String s2 = "aaa";
        System.out.println(s1 == s2);//true

        String s3 = new String("aaa");
        String s4 = new String("aaa");

        System.out.println(s3 == s4);//false
        System.out.println(s3.equals(s4));//false
    }


}


class MyTime{
    int year;
    int month;
    int day;

    public MyTime(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyTime)) return false;
        MyTime myTime = (MyTime) o;
        return year == myTime.year &&
                month == myTime.month &&
                day == myTime.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}
