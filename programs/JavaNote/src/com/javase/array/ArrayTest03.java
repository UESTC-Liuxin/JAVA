package com.javase.array;

public class ArrayTest03 {



    public static void main(String[] args) {
        Animal a=new Bird();
        Animal b=new Dog();

        Animal[] animals={a,b};
        for (Animal animal:animals) {
            animal.move();
        }
    }
}

class Animal{
    public void move(){
        ;
    }
}


class Bird extends Animal{
    public void move(){
        System.out.println("flying...");
    }
}

class Dog extends Animal{
    @Override
    public void move() {
        System.out.println("runing...");
    }
}