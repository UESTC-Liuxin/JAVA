package com.javase.polymorphism;

public class polymorphismTest {
    public static void main(String[] args) {
        Animal animal =new Dog();
        animal.eat();
    }
}

class Animal{
    public Animal() {
    }

    public void eat(){
        System.out.println("animal is eating...");
    }

}


class Dog extends Animal{
    public Dog() {
    }

    @Override
    public void eat() {
        System.out.println("dog is eating...");
    }
}