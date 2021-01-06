package com.javase.collection;

public class GenericTest01<标识符随便写> {
    public static void main(String[] args) {
        //new对象的时候指定了泛型是：String类型
        GenericTest01<String> gt = new GenericTest01<>();
        gt.doSome("abc");

        GenericTest01<Integer> gt1 = new GenericTest01<>();
        gt1.doSome(123);

        String s="abc";
        System.out.println(s.hashCode());
        s+="def";
        System.out.println(s.hashCode());
    }

    public void doSome(标识符随便写 o){

    }
}
