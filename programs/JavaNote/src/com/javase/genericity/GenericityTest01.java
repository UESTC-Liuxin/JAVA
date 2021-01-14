package com.javase.genericity;

import java.util.ArrayList;
import java.util.List;

public class GenericityTest01 {
    public static void main(String[] args) {
        List<? extends Animal> list = new ArrayList<Dog>();
//        list.add(new Dog());
//        Animal animal=list.get(0);
        List<? super Dog> list1 = new ArrayList<Dog>();
        list1.add(new Dog());
    }
}

class Animal{

}

class Dog extends Animal{

}
class Cat extends Animal{

}