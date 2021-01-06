package com.javase.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericTest {
    public static void main(String[] args) {

        List<Animal> l = new ArrayList();

        Animal a = new Cat();
        Animal b = new Bird();

        l.add(a);
        l.add(b);

        Iterator<Animal> it = l.iterator();
        while (it.hasNext()) {
            Animal animal = it.next();
            animal.move();
        }
    }
}


class Animal{
    public void move(){
        System.out.println("Animal moving...");
    }

}

class Cat extends Animal{
    @Override
    public void move() {
        System.out.println("Cat runing...");
    }

    public void catchMouse(){
        System.out.println("Catching mouses...");
    }

}


class Bird extends Animal{
    @Override
    public void move() {
        System.out.println("Bird flying...");
    }

    public void fly(){
        System.out.println("Flying....");
    }
}

