# 泛型

## 1. ? extends T 与 ? super T

？通配符类型

- <? extends T> 表示类型的上界，表示参数化类型的可能是T 或是 T的子类;(上界通配符)
- <? super T> 表示类型下界，表示参数化类型是此类型的超类型（父类型），直至Object;(下界通配符)

**上界通配符<? extends T>不能往里存，只能往外取**

首先要明确一个概念，这段代码是不合法的（会直接报错）：

```java
public class GenericityTest01 {
    public static void main(String[] args) {
        List<Dog> list = new ArrayList<>();
        list.add(new Animal());//不合法
        list.add(new Cat());//不合法
    }
}

class Animal{

}

class Dog extends Animal{

}
class Cat extends Animal{

}
```

那么由于<? extends T>可以表示T 或是 T的子类，所以对于<? extends Animal>来说，可以表示Animal和Dog和Cat中的一种，因此下列代码是合法的。

```java
List<? extends Animal> list = new ArrayList<Dog>();
```

但是对于编译器来说，list具体是什么样的类型，编译器是不知道的，因此为了避免Cat对象和Animal对象被添加入list中，添加操作将不被允许：

```java
List<? extends Animal> list = new ArrayList<Dog>();
list.add(new Dog());
```

但是以下语法却是可以的（因为无论是Animal还是Dog还是Cat，我们都能通过隐式地向上转型到Animal）：

```java
List<? extends Animal> list = new ArrayList<Dog>();
Animal animal=list.get(0);
```

**下界<? super T>不影响往里存，但往外取只能放在Object对象里**

首先要明确一个概念，这段代码也是不合法的：

```java
List<Animal> list2 =new ArrayList<>();
Dog dog= list2.get(0);
```

<? super Dog>可以表示Dog的父类（以及向上的父类）

```java
List<? super Dog> list1 = new ArrayList<Dog>();
```

对于编译器来说，list1存的具体是什么类别呢？编译器只知道是Dog的超类，但是具体是Object还是Animal他时不知道的，因此，以下代码是不合法的（如何合法，可能会出现上面list2获取Dog对象的错误情况）：

```java
List<? super Dog> list1 = new ArrayList<Dog>();
list1.add(new Dog());
Dog d=list1.get(0);
```

但是，由于隐式地向上转型，所以，下面代码是合法的：

```java
List<? super Dog> list1 = new ArrayList<Dog>();
list1.add(new Dog());
```

因为至少无论list1当中存储的实际类型是什么（无论是Object还是Animal还是Dog，往里添加一个Dog对象都是可以的）。