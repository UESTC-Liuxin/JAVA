package com.javase.array;

public class MyStack {
    private Object[] elements;
    private int index;

    public MyStack() {
        //一维数组动态初始化
        this.elements=new Object[10];
        this.index =-1;

    }

    /**
     * 压栈的方法
     * @param element
     * @return
     */
    public boolean push(Object element){
        if(index>= elements.length){
            System.out.println("压栈失败，栈已满！");
            return false;
        }

        index++;
        elements[index]=element;
        return true;
    }

    /**
     *
     * @return
     */
    public Object pop(){
        if(index<0){
            System.out.println("栈已空...");
            return null;
        }
        return elements[index--];
    }

    public MyStack(Object[] elements) {
        this.elements = elements;
    }

    public void setElements(Object[] elements) {
        this.elements = elements;
    }

    public Object[] getElements() {
        return elements;
    }
}

class Cat{
    public String name;

    public Cat() {
    }

    public Cat(String name) {
        this.name=name;
    }
}

class MyStackTest{
    public static void main(String[] args) {
        MyStack stack =new MyStack();
        Cat cat=new Cat();
        System.out.println(cat);
        stack.push(cat);
        Cat newCat=(Cat) stack.pop();
        System.out.println(newCat);
    }
}
