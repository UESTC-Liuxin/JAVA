package com.javase.inherit;

public class OverrideTest {
    public static void main(String[] args) {
        Person person=new Person();
        person.setName("liuxin");
        System.out.println(person);

    }
}


class Person extends Object{
    private String name;
    public Person(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "com.javase.inherit.Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
