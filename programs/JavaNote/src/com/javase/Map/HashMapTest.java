package com.javase.Map;

import java.util.HashMap;
import java.util.Objects;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap map = new HashMap();
    }
}


class Student{
    private String name;
    private int age;

    Student(String name) {
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getName(), student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}


