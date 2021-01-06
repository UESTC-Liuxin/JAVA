package com.javase.io;

import java.io.*;

public class Serialize_DeserializeTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students"));
        oos.writeObject(new Student("liuxin",24,new IdCard(100)));
        oos.flush();
        oos.close();
        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students"));
        Object Obj = ois.readObject();
        ois.close();
        System.out.println(Obj); //Student{name='liuxin', age=24}
    }
}

class IdCard implements Serializable{
    private int money;

    public IdCard(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
class Student implements Serializable {
    private String name;
    private int age;
    public IdCard card;
    //指定序列化版本号
    private static final long serialVersionUID = -20210103121545L;

    public Student(String name, int age, IdCard card) {
        this.name = name;
        this.age = age;
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + card.getMoney() +
                '}';
    }
}