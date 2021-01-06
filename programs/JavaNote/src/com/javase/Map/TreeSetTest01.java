package com.javase.Map;
import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest01 {
    public static void main(String[] args) {

        //利用比较器实现
        TreeSet<Person> persons = new TreeSet<>(new PersonComparetor());

        persons.add(new Person(10));
        persons.add(new Person(11));
        persons.add(new Person(9));

        for(Person p:persons){
            System.out.println(p.getAge());
        }

        //利用Comparator实现
        TreeSet<Man> men =new TreeSet<>();
        men.add(new Man(9));
        men.add(new Man(11));
        for (Man man:men){
            System.out.println(man.getAge());
        }
    }
}


class Person{
    private int age;

    public Person(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }
}


class PersonComparetor implements Comparator<Person> {
    @Override
    public int compare(Person person, Person t1) {
        return person.getAge()-t1.getAge();
    }
}



class Man extends Person implements Comparable<Man>{
    public Man(int age) {
        super(age);
    }

    @Override
    public int compareTo(Man man) {
        return getAge()-man.getAge();
    }
}