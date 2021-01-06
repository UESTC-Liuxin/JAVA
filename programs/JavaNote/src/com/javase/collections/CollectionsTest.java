package com.javase.collections;

import java.util.*;

public class CollectionsTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        //变化线程安全的
        Collections.synchronizedList(list);

        //排序
        list.add("a");
        list.add("c");
        Collections.sort(list);
        for(String s: list)
            System.out.println(s);

        //对于自定义的类型的排序，需要实现Compareble接口
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(10));
        persons.add(new Person(22));
        persons.add(new Person(12));
        Collections.sort(persons);
        for(Person person:persons)
            System.out.println(person.getAge());

        //对Set集合怎么排序
        Set<Person> set = new HashSet<>();
        set.add(new Person(10));
        set.add(new Person(22));
        set.add(new Person(12));
        List<Person> list1 = new ArrayList<>(set);
        Collections.sort(list1);
        for(Person person:list1)
            System.out.println(person.getAge());
    }

}


class Person implements Comparable<Person>{
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

    @Override
    public int compareTo(Person person) {
        return age-person.age;
    }
}
