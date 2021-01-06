package com.javase.inner;

public class Inherit {
    public static void main(String[] args) {
        B b=new B();
    }

}


class A{
//    public com.javase.inner.A(){
//        System.out.println("com.javase.inner.A is called");
//    }
    public A(int i){
        System.out.println("com.javase.inner.A is called");
    }
    public void test(){
        System.out.println("com.javase.inner.A is tested");
    }
}

class B extends A{
    public B(){
        super(9);
        System.out.println("com.javase.inner.B is called");
        super.test();
    }
    public void test(){
        System.out.println("com.javase.inner.B is tested");
    }
}