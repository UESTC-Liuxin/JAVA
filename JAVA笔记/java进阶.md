#  final与常量

final是一个关键字，表示最终的，不可变的

修饰：变量、方法、还有类

- 修饰变量

  final修饰局部变量一旦赋值，就不能更改，和常量不同，不需要在定义时赋值

  final修饰**实例变量**必须要手动赋值，不会赋默认值。==final修饰的实例变量，一般添加static修饰。==其实我觉得这里有问题，对于一个对象，如果在实例化一个对象时对其进行一个初始化，然后此值不再允许改变。比如person对象的ID，此ID初始化后，不能再改变，但是每个对象都不一样。

  static final联合修饰的变量称之为“常量”，用大写。

  ```java 
  class FinalTestA{
      final int age;  //错误写法
      public void doSome(){
          final int m;
          System.out.println("do some thing ....");
      }
  
  }
  class FinalTestB extends FinalTestA{
      public static void main(String[] args) {
  
      }
  }
  ```

- 修饰方法

  不可被覆盖和重写

- 修饰类

  修饰 的类无法被继承,比如String类

  ```java
  public final class String
      implements java.io.Serializable, Comparable<String>, CharSequence,
                 Constable, ConstantDesc {
                 }
  ```

  举例：

  ```java
  final class FinalTestA{
      public void doSome(){
          System.out.println("do some thing ....");
      }
  
  }
  class FinalTestB extends FinalTestA{
      public static void main(String[] args) {
      }
  }
  ```

# 抽象类

  ## 什么是抽象类？

 类和类之间具体共同特征，将这些共同特征提取出来，形成的就是抽象类。

## 抽象类属于什么类型？

抽象类，也属于引用数据类型。

## 怎么定义？

```java
[修饰符列表] abstract class 类名{
	类体
}
```

## 注意事项

抽象类无法实例化，只能被继承。抽象类虽然不能被实例化，但是抽象类可以有构造方法，此构造方法供子类使用。==注意：构造方法是不能被继承的。==

```java
public class ObstractTest {
    public static void main(String[] args) {
        new CreditAccount();
    }
}
abstract class Account{

}
class CreditAccount extends Account{

}
```

抽象方法只能出现在抽象类中，但是抽象类可以不用出现抽象方法。==抽象方法不能有函数体，必须在子类中进行实现。==

**面试题**

java语言中凡是没有方法体的方法都是抽象方法。不对，错误的。

Object类中很多方法都没有方法体，都是以“；”结尾的，但他们都不是抽象方法，例如：

```java
public native int hashCode();
```

这个方法底层调用了C++写的动态链接库程序。前面修饰符列表中没有:abstract。有一个native。表示调用JVM本地程序。

# 接口

## 基础语法

- 接口也是一种引用数据类型，接口是完全抽象的，抽象类是半抽象的。

```java
[修饰符列表] interface 接口名{
	类体
}
```

- 接口支持多继承，一个接口允许继承多个接口。

```JAVA
interface InterfaceC extends InterfaceA,InterfaceB{

}
```

- 接口里的方法都是public abstract的，因此不用写。。。，同时一定**不能有方法体**。

- 接口里的变量都是常量，无法定义真正的变量。所有变量都会被默认加上`public static final`

- 接口中所有成员都是public的。

- 类与接口之间叫实现。

  ```java
  interface MyMath{
   double PI=3.1415926;
   int add(int a,int b);
  }
  class MathTest implements MyMath{
      //实现接口中的方法,必须加public,因为方法重写必须访问权限只能变宽，不能变严
      public int add(int a,int b){
          return 0;
      }
  }
  ```

- 接口也存在多态。

  ```java
  public class InterfaceTest {
      public static void main(String[] args) {
          MyMath myMath = new MathTest();
          System.out.println(myMath.add(1,2));
      }
  }
  ```

- 类可以进行多实现。

  ```java
  class InfoProcessTest implements MyMath,MyDigitalProcess{
      //实现接口中的方法
      public int add(int a,int b){
          return a+b;
      }
  
  }
  ```

- 接口与接口之间在进行强制类型转换的时候，没有继承关系，也可以强转，但是运行时可能会出现ClassCastException异常。

- 当实现了多个接口，使用多态时，只能调用向上转型中的方法。

- 继承和实现同时存在的时候怎么写？

  继承和实现同时有的时候，继承在前，接口在后。extends在前，implements在后
  
  ```java
  class InfoProcessTest extends  BaseProcess implements MyMath,MyDigitalProcess{
      //实现接口中的方法
      public int add(int a,int b) {
          return a+b;
      }
  
      public double multiSigmma(double a){
          return a*sigmma;
          
      }
  }
  ```

## 具体作用

接口在开发中的作用，类似与多态在开发中的作用。

多态：面向对象时，不要面向对象编程。降低程序的耦合度。提高程序的扩展力。

面向抽象编程就可以修改为：面向接口编程。

具体作用：

```java
public class InterfaceTest2 {

    public static void main(String[] args) {
        ChineseCooker chineseCooker =new ChineseCooker();
        Customer customer =new Customer(chineseCooker);
        customer.order();

    }
}
//提供了两个实现
class WestCooker implements FoodMenu{
    public void doSomeNoodles(){
        System.out.println("do some noodles by WestCooker!");
    }
    public void doSomeRices(){

    }
}
class ChineseCooker implements FoodMenu{
    public void doSomeNoodles(){
        System.out.println("do some noodles by ChineseCooker!");
    }
    public void doSomeRices(){
        
    }
}

//菜单就是接口
interface FoodMenu{
    void doSomeNoodles();
    void doSomeRices();
}

class Customer{
    private FoodMenu foodMenu;
    public Customer(){ }
    
    //利用多态，可以解耦
    public Customer(FoodMenu foodMenu) {
        this.foodMenu = foodMenu;
    }
    public void setFoodMenu(FoodMenu foodMenu) {
        this.foodMenu = foodMenu;
    }
    public FoodMenu getFoodMenu() {
        return foodMenu;
    }
    public void order(){
        foodMenu.doSomeNoodles();
    }
}
```

总结一句话：利用接口可以将功能进行解耦，比如：当我们定义好了FoodMenu，Customer和WestCooker就可以交给不同的人完成。



## 抽象类与接口的区别

- 抽象类是半抽象的。

- 接口是完全抽象的。
- 抽象类中有构造方法。
- 接口中没有构造方法。
- 接口和接口之间支持多继承
- 类与类之间只能单继承
- 一个类可以同时实现多个接口
- 一个抽象类只能继承一个类（单继承）
- 接口中只允许出现常量和抽象方法
-  **抽象类出现较少，接口一般都是对“行为”进行抽象**

# 类与类之间的关系

1. **继承**
2. **关联关系 （类内变量）**
3. **实现关系（接口）**

# 包机制

关于java与语言的package与import机制

1. 为什么要使用package？

   package是java中的包机制。包机制的作用是为了方便程序的管理。不同功能的类分别存放在不同的包下。（**按照功能划分的，不同的软件包具有不同的功能**。

2. packge怎么用？

   packge是一个关键字，后面加包名：

   ```java
   packge com.java.AA
   ```

   这句话只允许出现在java源代码的第一行。

3. 命名规范？

   公司域名倒叙+项目名+模块名+功能名。

4. 怎么编译？运行？

   假如包的路径是com.uestc.javase.packageTest

   类名不再是Test，而是com.uestc.javase.packageTest.Test

   编译：

   javac -d .Test.java

   解释一下：

   - javac 负责编译的命名。
   - -d 带包编译
   - 代表编译之后生成的东西方法哦当前目录下（点代表当前目录）
   - Test.java 被编译的java文件名。

   运行：
   
   java com.uestc.javase.packageTest.Test
   
   ==注意：这尼玛，，，jdk15不行，弄了半天。==
   
  5.  import什么时候用

     A类与B类不在同一package之中。

6. java.lang下的包不需要导，默认导



# 访问控制符



![](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/Lyj4Fc6JAgRdYun.png)
# 源码以及API文档

## Object类

常用方法：

```java
protected Object clone()  //负责对象克隆的
int hashCode() //获取对象哈希值的一个方法
boolean equals(Object obj)  //判断两个对象是否相等
String toString()  //
protected void finalize()  //垃圾回收器负责调用的方法
```

- toString()

```java
public String toString() {
    return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode());
}
//默认实现是返回一个字符串，该字符串由类名（对象是该类的一个实例）、at 标记符“@”和此对象哈希码的无符号十六进制表示组成
```

- equals()

```java
//equals是判断两个对象是不是相等的。
    public boolean equals(Object obj) {
        return this == obj;
    }

//一般查看的此类的对象的属性是否相等。默认实现是判断两个对象的内存的地址
//重写equals考虑几种情况：为null，不是同类型对象， 内存地址相同。

//在idea中可以自动生成

```

==尤其注意的是，对于对象而言，“\=\=“并没有被重载，需要用equals方法。如果对象内还有引用对象，需要引用对象的equals的方法进行重写，重写要彻底。==

但是对于String来说，两种定义方式，得到的结果是不同的，这里涉及到**常量池**的问题，有不同。

```java
public class EqualsTest {
    public static void main(String[] args) {
        MyTime t1= new MyTime(2008,1,1);
        MyTime t2=new MyTime(2008,1,1);
        System.out.println(t1.equals(t2));

        String s1="aaa";
        String s2="aaa";
        System.out.println(s1==s2);//true

        String s3=new String("aaa");
        String s4=new String("aaa");

        System.out.println(s3==s4);//false
        System.out.println(s3.equals(s4));//false
    }
}
```

- clone()

对于引用类型来说，`=`赋值符号等于将其引用复制一份；而利用clone才能将其对象真正复制一遍：这里还有一个区分，就是浅拷贝和深拷贝。浅拷贝只拷贝对象，而深拷贝则是对对象内所有的引用对象都创建一个副本进行拷贝。

关于clone的实现：

```java
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        ClassA a=new ClassA();
        ClassA b=(ClassA) a.clone();
    }
}

class ClassA implements Cloneable{
    String name;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        ClassA obj = (ClassA) super.clone();
        return obj;
    }
}

```

重写clone的类，必须要实现Cloneable，同时，在调用clone方法的方法，必须添加：`CloneNotSupportedException` ，其意义是：如果对象的类不支持 `Cloneable` 接口，则重写 `clone` 方法的子类也会抛出此异常，以指示无法克隆某个实例。

- hashCode()

  实际上就是对象地址的哈希编码，可以等同看做内存地址。


# 内部类

1. 什么是内部类？

   内部类：在类的内部又定义了一个新的类，被称为内部类。

   - 静态内部类：类似于静态变量
   - 实例内部类：类似于实例变量
   - 局部内部类：类似于局部变量

   ==使用内部类的代码，可读性差，尽量不用==

2. 匿名类

   匿名类是局部内部类的一种

    用法：

   ```java
   public class InnerClass {
   
   
       public static void main(String[] args) {
           InnerClass innerClass =new InnerClass();
           innerClass.mySum(
                   //定义一个匿名内部类实现接口
                   new Sum(){
               public int sum(int a,int b){return a+b;}
           },
                   100,
                   200
           );
       }
   
       public int mySum(Sum s,int a,int b){
           return s.sum(a,b);
       }
   
   }
   
   interface Sum{
       public int sum(int a,int b);
   }
   ```

# 一维数组

 Array

1. 数组是引用类型。不属于基本数据类型。数组的分类是object。

2. 数组实际上是一个容器，可以同时容纳多个元素

   数组：字面意思是“一组数据”

3. 数组当中可以储存所有数据类型

4. 数组存在堆内存当中。

5. 数组当中如果储存的是"java"对象的话，实际上储存的是对象的“引用(内存地址)”

6. 数组在内存方面，地址是连续的。

   对于引用类型来说，其存放引用的地址是连续的，但是引用对象的地址不是连续的

   ![image-20201204221814635](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201204221814635.png)

7. 同C/C++一样，数组的地址是数组内存的首地址

8. 优点：

   - 内存连续；
   - 元素类型相同；
   - 内存地址可以在知道下标的情况下计算出来，时间复杂度为O(1);

   缺点：

   - 由于为了保证数组中每个元素的内存地址连续，所以在数组上随机删除或者增加元素的时候效率较低没因为随机增删元素会涉及到后面元素的同意向前或者向后位移的操作。==增删最后一个数组没有影响==

   - 不能储存大数据量，为什么？

     因为很难在内存空间上找到连续的内存空间。

9. 初始化一维数组

   - 静态初始化：int[] array={1,2,3,4}
   - 动态初始化：int[] array=new int[6]

10. 参数的传递：

    - 静态数组传递：funcA(new int[]{1,2,3});//不能写长度
    - 数组属于引用类型，参数传递的是引用

11. 关于数组的长度为0，和为null的关系：

    ```java
    int[] b={};
    int[] c=null;
    
    System.out.println(b.length);//输出为0
    System.out.println(c.length);//空指针异常
    ```

12. 关于main中的args数组

    用于接受命令行参数，参数以空格隔开。

13. 多态机制与数组的结合

    ```java
    package uestc.javase.array;
    
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
    ```

14. 数组拷贝

  System.arraycopy

  我们还可以使用System类的arraycopy()方法将数组的元素复制到另一个数组。
    	  arraycopy()方法的签名如下:

```java
public static  void  arraycopy(Object sourceArray, 
                               int  sourceStartPosition, 
                               Object destinationArray,
                               int destinationStartPosition, 
                               int  lengthToBeCopied)
```

这里，

- sourceArray是源数组。
- sourceStartPosition是源数组中的起始索引，元素的复制将从该起始索引开始。
- destinationArray是目标数组。
- destinationStartPosition是目标数组中的起始索引，将从中复制源数组的新元素。
- lengthToBeCopied是要复制的元素数。



# 二维数组

1. 二维数组静态初始化，可以每一维长度不相同

   ```java
   int[][] a={
           {1,2,3},
           {1,2,1,3},
           {0}
   };
   ```



# String

1. String属于引用数据类型

2. 字符串常量不可变，直接存储在“**方法区**”中的**字符串常量池**当中，不是在堆中

3. 对于new出来的String对象，都在堆中：

   ```java
   public class StringTest01 {
       public static void main(String[] args) {
   
           String s3=new String("abc");
       }
   
   }
   ```

   对于此代码，s对象储存在堆中，但是“abc”存储在方法区的字符串常量池中。

   ![image-20201209004331040](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201209004331040.png)

   如果对象中存在字符串属性：

   

![image-20201209004743854](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201209004743854.png)

根据上面内存图，容易知道：

```java
public class StringTest01 {
    public static void main(String[] args) {

        String s=new String("abc");
        String s1="abc";
        String s2="abc";
        System.out.println(s==s1); //s指向堆中的一个对象，s1指向字符串常量池
        System.out.println(s1==s2);//指向同一个字符串常量池
    }

}
```

输出：

false

true

因此，字符串对象的相等判断要用equals方法。

```java
System.out.println(s1.equals(s));
```

true

关于更多的细节：https://www.cnblogs.com/lykxbg/p/13656261.html

4. String类型重写了toString()方法。

5. 常用方法：
   - charAt
   
   - compareTo
   
   - contains
   
   - endsWith
   
   - equals
   
   - equalsIgnoreCase//忽略大小写
   
   - getBytes  //将字符串对象转换成字节数组
   
   - indexOf //首次出现的索引值
   
   - lastIndexOf  //最后出现的索引值
   
   - replace
   
   - replaceAll //用正则表达式
   
   - subString
   
   - toCharArray  //将字符串转为字符数组
   
   - toLowCase  //转换为小写
   
   - trim  //去除字符串前后空白
   
     ```java
     import java.io.*;
     public class Test {
     
        public static void main(String args[]) {
           String Str = new String("   Welcome to Tutorialspoint.com   ");
     
           System.out.print("Return Value :" );
           System.out.println(Str.trim() );
        }
     }
     ```
   
   - valueOf //数字转字符串：==参数可以为Obj==
   
   ## StringBuffer类
   
   与String字符串常量不同，StringBuffer是一个字符串变量，其对象允许扩充和修改，数据储存在堆中，而不在字符串常量池中：
   
   ```java
   for(int i=0;i<100;i++){
       s+=i;
   }
   ```
   
   StringBuffer实际上内部利用一个Byte的数组对字符串进行储存，保存的是对堆中的对象的引用。而String类保存的是一个字符串常量池中常量字符串的引用。
   
   ==但其实，StringBuffer在实际过程中用得并不频繁，通常使用是s+String.valueOf("A")进行字符串的拼接。至于这样做不会再造成字符串常量池的额外开销了，因为valueOf后的字符串存在堆中。==
   
   与StringBuilder的区别是：StringBuilder是线程不安全的。。。
   
   <img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201215234242283.png" alt="image-20201215234242283" style="zoom:80%;" />

# 包装类

```java
class MyInt{
    int value;

    public MyInt(int value) {
        this.value=value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}


public class IntegerTest {
    public static void main(String[] args) {
        MyInt myInt = new MyInt(100);
        doSome(myInt);
    }

    public static void doSome(Object obj){
        System.out.println(obj);
    }
}
```

利用包装类，可以将基本数据类型变成对象，实际上在新版本中基本数据类型也能进行自动装箱。

8中包装类，==8种数据类型不够用==

8种包装类都继承于class Number

```JAVA
public abstract class Number implements Serializable {
    private static final long serialVersionUID = -8742448824652078965L;

    public Number() {
    }
    public abstract int intValue();
    public abstract long longValue();
    public abstract float floatValue();
    public abstract double doubleValue();
    public byte byteValue() {
        return (byte)this.intValue();
    }
    public short shortValue() {
        return (short)this.intValue();
    }
}
```

这几个抽象方法都是将引用数据类型转为基本数据类型。

将基本数据类型转换为引用类型，叫做**装箱**，反之，成为**拆箱**。

## 自动装箱与自动拆箱

在java5之后，引入了一种新特性，自动装箱和自动拆箱

```java
Integer integer = 100;
int new_integer = integer;
```

- 自动装箱

  ```java
  //自动装箱
  Integer a=1010;
  //自动拆箱
  int b=a+1;
  ```

  自动装箱的意思是，在执行上述代码时，自动装箱会自动new一个Integer对象，初始化为1010。

- 自动拆箱

  在运算的时候可自动执行自动拆箱：

- 整数常量池的概念

  分析下列代码：

  ```java
  public class IntegerTest01 {
      public static void main(String[] args) {
  
          Integer a=128;
          Integer b=128;
          System.out.println(a==b);//false
  
          Integer x=127;
          Integer y=127;
  
          System.out.println(x==y);//true
      }
  }
  ```

  **java中为了提高程序效率，将[-128,127]之间所有的包装对象提前创建好，放到一个方法区的“整数型常量池”当中，目的是只要用这个区间的数据不需要再new了，直接从整数型常量池当中取出来。**

  所以，x与y的地址是一样的，“==“永远了。

  内存图如下：

  ![image-20201216000227004](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201216000227004.png)

## Integer类

- 构造函数：

  Integer(int value)

  Integer(String s)

- 常用方法

  compareTo()

  parseInt()

  toHexString()

  static Integer valueOf(String s)

  ...

## String int Integer互相转换

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201216100851278.png" alt="image-20201216100851278" style="zoom:80%;" />

```java
public class Integer2int2String {
    public static void main(String[] args) {
        //String2int
        String s="123";
        int num =Integer.parseInt(s);
        //int2String
        String s1=String.valueOf(num);
        //int2Integer
        Integer numObj=num;
        //Integer2int
        int num1=numObj;
        //Integer2String
        String s2=String.valueOf(num1);
        //String2Integer
        Integer numObj1=Integer.valueOf(s2);

    }
}
```

# 对日期的处理

- 构造函数

  Public Date()：无参构造函数，获取当前时间

  ```java
      public Date() {
          this(System.currentTimeMillis());
      }
  ```

  public Date(long date)

  public Date(int year, int month,int date)

  public  Date(long Date)

  ...

- 关于格式化日期

  ```java
   Symbol   Meaning                 Presentation        Example
   ------             -------                 ------------        -------
   G        		era designator          (Text)              AD
   y        		year                    (Number)            1996
   M        		month in year           (Text & Number)     July & 07
   d        		day in month            (Number)            10
   h       		 hour in am/pm (1~12)    (Number)            12
   H        		hour in day (0~23)      (Number)            0
   m      	   minute in hour          (Number)            30
   s       		 second in minute        (Number)            55
   S    		    millisecond             (Number)            978
   E       		 day in week             (Text)              Tuesday
   D     		   day in year             (Number)            189
   F    	 	   day of week in month    (Number)            2 (2nd Wed in July)
   w      	  week in year            (Number)            27
   W      	  week in month           (Number)            2
   a  	      am/pm marker            (Text)              PM
   k     		   hour in day (1~24)      (Number)            24
   K  	      hour in am/pm (0~11)    (Number)            0
   z     		   time zone               (Text)              Pacific Standard Time
   '      		  escape for text         (Delimiter)
   ''     	  single quote            (Literal)           '
  ```

  **Examples Using the US Locale:**	

  |   Format Pattern        |                 									Result|
  | ---- | ---- |
  |  "yyyy.MM.dd G 'at' hh:mm:ss z" 				|  1996.07.10 AD at 15:08:56 PDT |
  |  "EEE, MMM d, ''yy"              						| Wed, July 10, '96 |
  |"h:mm a"                        						| 12:08 PM |
  | "hh 'o''clock' a, zzzz"         		| 12 o'clock PM, Pacific Daylight Time |
  | "K:mm a, z"                     		| 0:00 PM, PST|
  |"yyyyy.MMMMM.dd GGG hh:mm aaa"  | 1996.July.10 AD 12:08 PM |
  
  ```java
  public class DateTest01 {
      public static void main(String[] args) {
          Date nowTime =new Date();
          System.out.println(nowTime);//Wed Dec 16 10:53:36 CST 2020
  
  
          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss SSS");
          String nowTimeStr= sdf.format(nowTime);
          System.out.println(nowTimeStr);//2020-12-16 10:12:36 672
  
      }
  }
  ```

- 字符串转Date

  ```java
  Date nowTime =new Date();
  System.out.println(nowTime);//Wed Dec 16 10:53:36 CST 2020
  
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss SSS");
  String nowTimeStr= sdf.format(nowTime);
  System.out.println(nowTimeStr);//2020-12-16 10:12:36 672
  
  String time="2020-12-16 0:0:0 000";
  Date dateTime = sdf.parse(time);
  System.out.println(dateTime);//Mon Dec 16 00:00:00 CST 2019 没有毫秒，因为toString没有实现，实际上次存在
  ```

  ==注意：字符串的格式必须与format格式一致，不然就会报出异常。==

- 记录运行时间

  ```java
  public class DateTest2 {
      public static void main(String[] args) throws InterruptedException {
          //获取自1970年1月1日 00:00:00 000到当前系统时间的总毫秒数
          long preTimeMillis=System.currentTimeMillis();
          //System.out.println(preTimeMillis);//1608088051645
  
          //统计一个方法耗时
          //在调用目标方法之前记录一个毫秒数
          Thread.sleep(100);//睡眠100 ms
          long endTimeMillis=System.currentTimeMillis();
          System.out.println(endTimeMillis-preTimeMillis);//100
  
      }
  }
  ```

# 数字格式化

对于数字的格式化表示，可用**DecimalFormat**类来表示。

| Symbol     | Location            | Localized? | Meaning                                                      |
| ---------- | ------------------- | ---------- | ------------------------------------------------------------ |
| 0          | Number              | Y          | Digit                                                        |
| #          | Number              | Y          | Digit, zero shows as absent                                  |
| .          | Number              | Y          | Decimal separator or monetary decimal separator              |
| -          | Number              | Y          | Minus sign                                                   |
| ,          | Number              | Y          | Grouping separator                                           |
| E          | Number              | Y          | Separates mantissa and exponent in scientific notation. *Need not be quoted in prefix or suffix.* |
| ;          | Subpattern boundary | Y          | Separates positive and negative subpatterns                  |
| %          | Prefix or suffix    | Y          | Multiply by 100 and show as percentage                       |
| \u2030     | Prefix or suffix    | Y          | Multiply by 1000 and show as per mille                       |
| ¤ (\u00A4) | Prefix or suffix    | N          | Currency sign, replaced by currency symbol. If doubled, replaced by international currency symbol. If present in a pattern, the monetary decimal separator is used instead of the decimal separator. |
| '          | Prefix or suffix    | N          | Used to quote special characters in a prefix or suffix, for example, `"'#'#"` formats 123 to `"#123"`. To create a single quote itself, use two in a row: `"# o''clock"`. |

```java
public class DecimalFormatTest {
    public static void main(String[] args) {

        DecimalFormat df=new DecimalFormat("#####,##.0000");
        System.out.println(df.format(12222.145));//1,22,22.1450
    }
}
```



# BigDecimal

对于某些财务软件的开发，常规的double不够用，因此需要超大数

#　随机数(Random)

- 构造函数：

  Random()

  Random(long seed)

- 方法:

  public void setseed(long seed) //设置随机种子

  public int nextInt(int n)  //产生一个整数型伪随机数，可选（边界）

  public nextGaussian() //产生一个符合高斯分布的伪随机数

    ```java
  double doubleNum=random.nextGaussian();
  System.out.println(doubleNum);

  int intNum=random.nextInt(10);
  System.out.println(intNum);
    ```

- 生成不重复的随机数


# 枚举

枚举也是一个class.

```java
enum 枚举类型名{
	枚举值1，枚举值2，枚举值3
}
```

用于可列举的大于2的情况，其打印值为枚举值。

```java
public class EnumTest {
    public static void main(String[] args) {
        System.out.println(Sex.MEN);//MEN
    }

}
enum Sex{
    MEN,WOMEN
}
```

**switch函数支持枚举类型，但是必须要同一个类型。**

```java
public class EnumTest {
    public static void main(String[] args) {
        System.out.println(Sex.MEN);

        switch (Season.SPRING){
            case SPRING:    //可省略Season
                System.out.println(Season.SPRING);
                break;
            case SUMMER:
                System.out.println(Season.SUMMER);
                break;
            case AUTUMN:
                System.out.println(Season.AUTUMN);
                break;
            case WINTER:
                System.out.println(Season.WINTER);
                break;
            default:
                break;
        }
    }

}
enum Sex{
    MEN,WOMEN
}
enum Season{
    SPRING,SUMMER,AUTUMN,WINTER
}
```

# 异常

java语言中的异常是class，而且可实例化

```java
public class ExceptionTest {
    public static void main(String[] args) {
        NumberFormatException nfe =new NumberFormatException("数字格式化异常");
        System.out.println(nfe);

    }
}

```

实际上，当我们发生异常时，jvm是创建了一个异常对象，然后抛出。

- Exception的继承关系

  - 运行时异常（所有的RuntimeException及子类都属于运行时异常。运行时异常在编写程序阶段，可以选择处理，也可以选择不处理。）在编写程序阶段，在编译时可以不处理

  - 非运行时异常（编译时异常），编译时异常不是在编译阶段发生的，是表示必须在编写程序时预先对这种异常进行处理。

  

  ![img](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/L3Byb3h5L2h0dHBzL2ltZzIwMTguY25ibG9ncy5jb20vYmxvZy8xNDIyMTAwLzIwMTkwNy8xNDIyMTAwLTIwMTkwNzI0MDk1ODQ5MTU3LTM3ODAyMTg0OC5wbmc=.jpg)

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/L3Byb3h5L2h0dHBzL2ltZzIwMTguY25ibG9ncy5jb20vYmxvZy8xNDIyMTAwLzIwMTkwNy8xNDIyMTAwLTIwMTkwNzI0MDk1OTA4MjY4LTE0OTQxNDk5MTIucG5n.jpg" alt="img" style="zoom: 50%;" />

- 异常的处理流程

<img src="https://bbsmax.ikafan.com/static/L3Byb3h5L2h0dHBzL2ltZzIwMTguY25ibG9ncy5jb20vYmxvZy8xNDIyMTAwLzIwMTkwNy8xNDIyMTAwLTIwMTkwNzI0MDk1OTI2NTM3LTExOTY1MTAzMDcucG5n.jpg" alt="img" style="zoom:50%;" />

- 编译时异常因为什么而得名？

  编译时异常必须在编译（编写）阶段预先处理，如果不处理，编译器会报错，因此得名。所有的异常都是在运行阶段发生的。因此只有程序运行阶段才可以new对象。因为异常的发生就是new异常对象。
  
- 对异常的处理

  第一种方式：在方法声明的位置上，使用throw关键字

  第二种方式：使用try...catch语句进行异常的捕捉

- 补充：

   [Java](http://lib.csdn.net/base/javaee)里面异常分为两大类:checkedexception(检查异常)和unchecked exception(未检查异常)，对于未检查异常也叫RuntimeException(运行时异常),对于运行时异常，java编译器不要求你一定要把它捕获或者一定要继续抛出，但是对checkedexception(检查异常)要求你必须要在方法里面或者捕获或者继续抛出。

- 检查异常举例

   ```java
   public class ExceptionTest01 {
   
       public static void main(String[] args) {
           doSome();//这里如果不进行任何处理就会报错
   
       }
       /**
        *
        * @throws ClassNotFoundException
        */
       public static void doSome() throws ClassNotFoundException{
   
       }
   }
   
   ```

   编译报错：

   ```bash
   java: 未报告的异常错误java.lang.ClassNotFoundException; 必须对其进行捕获或声明以便抛出
   ```

- 关于异常的处理方式举例

  - 第一种：利用throws关键字抛给上一层

    ```java
    public class ExceptionTest01 {
    
        public static void main(String[] args) throws ClassNotFoundException {
            doSome();//这里如果不进行任何处理就会报错
    
        }
        /**
         *
         * @throws ClassNotFoundException
         */
        public static void doSome() throws ClassNotFoundException{
    
        }
    }
    ```

  - 第二种，理由tray...catch..

    ```java
    public class ExceptionTest01 {
    
        public static void main(String[] args)  {
            try{
                doSome();//这里如果不进行任何处理就会报错
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        /**
         *
         * @throws ClassNotFoundException
         */
        public static void doSome() throws ClassNotFoundException{
    
        }
    }
    ```

    

- 总结：java中异常分为两类:checked exception(检查异常)和unchecked exception(未检查异常),对于未检查异常也叫RuntimeException(运行时异常).
  对未检查的异常(unchecked exception )的几种处理方式：
  1、捕获
  2、继续抛出
  3、不处理
  对检查的异常(checked exception，除了RuntimeException，其他的异常都是checked exception )的几种处理方式：
  1、继续抛出，消极的方法，一直可以抛到java虚拟机来处理
  2、用try...catch捕获
  **注意，对于检查异常必须捕获或者必须抛出，对于运行时异常可以不捕获或者不抛出或者不处理。**

  **在写代码时凡是要主动提示添加try/catch或者throws的异常，都是检查异常；**

  **在写代码时不主动提示添加try/catch或者throws的异常，都是运行时异常**
  
- try...catch ...细节

   - catch所处理的错误必须和上抛的错误类型相同，或者是其上抛类型的父类（也就是说可以全都用Exception类来处理）。

   ```java
   public class ExceptionTest01 {
   
       public static void main(String[] args)  {
           try{
               read();
           }
           catch (FileNotFoundException e){  //这里也可写成IOException
           }
       }
       /**
        *
        * @throws ClassNotFoundException
        */
       public static void doSome() throws ClassNotFoundException{
   
       }
       
       public static void read()  throws FileNotFoundException{
       }
   }
   ```

   - catch可以同时处理多种异常

   ```java
   public class ExceptionTest03 {
       public static void main(String[] args) {
           try{
               doSome();
           }
           catch (ClassNotFoundException | FileNotFoundException e){
   
           }
       }
   
       public static void doSome() throws ClassNotFoundException , FileNotFoundException{
   
       }
   }
   ```

- 异常对象的常见方法

   ```java
   public class ExceptionTest04 {
       public static void main(hashString[] args) {
           NullPointerException e =new NullPointerException("空指针异常");
           String msg= e.getMessage();
           System.out.println(msg);
   
           //打印异常堆栈信息，异步打印
           e.printStackTrace();
   
       }
   }
   ```

- finally子句

   在finally中的子句的代码是最后执行的，并且是一定会执行的，即使try语句块中的代码出现了异常。比如关闭流

   finally子句必须和try一起出现，不能单独编写。

   上述一定会执行指的是正常情况都会执行，包括以下情况：

   ```java
   public class ExceptionTest05 {
       public static void main(String[] args) {
           try{
               System.out.println("try..."); 
               return; //检测到return 会先执行finally
           }finally {
               System.out.println("finally...");  
           }
       }
   }
   ```

   但是System.exit(0)还是会让其退出。

- finally的面试题

   ```java
   public static void main(String[] args) {
       System.out.println(func());
   }
   
   public static int func(){
       int i=100;
       try{
           return i;//
       }finally {
           i++;
       }
   }
   ```

   java直上而下的执行顺序的原则是不会违背的，return的一定是i，而不是i++。

   所以这道题输出的时候就是100。

   反编译之后的执行顺序：

   ```java
   int i = 100;
   int j = i;
   i++;
   return j;
   ```

   

- final、finally、finalize的区别

   final是关键字，表示最终的不变的。

   finally是关键字，表示一定会执行的。

   finalize是标识符，一个类名。

- java自定义异常类

   ```java
   public class CustomException extends Exception{//
       public CustomException() {
       }
   
       public CustomException(String message) {
           super(message);
       }
   }
   
   
   class CustomExceptionTest{
       public static void main(String[] args) {
           CustomException e =new CustomException("自定义异常");
           e.printStackTrace();
       }
   }
   
   ```

- 方法重写不能比原方法抛出更多异常hash



# java集合

1. 集合概述：

   1.1. 什么是集合？有什么用？

   数组其实就是一个集合。集合实际上就是一个容器。可以用来容纳其他类型的数据。

   集合为什么说在开发中使用较多？

   集合是一个容器，是一个载体，可以一次容纳多个对象。
   
   1.2 集合不能直接存储基本数据类型，存的是对象的引用。
   
   1.3 在java中的每一个集合，底层会对应不同的数据结构，往不同的集合中存储元素，等于将数据放到了不同的数据结构中。什么是数据结构？数据存储的结构就是数据结构。
   
   1.4 集合在java.util下

2. 集合分类

   一类是单个方式存储元素：

   单个方式存储元素，这一类集合中超级父接口：java.util.Collection;

   一类是键值对的方式存储元素：

   以键值对的方式存储元素，这一类集合中超级父类接口：java.util.Map;

3. UML图

   ![img](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/402670-20191117185306543-1130864512.png)

   

## 接口总览

- **Iterater**
  - hasNext()
  - next()
  - remove()

- **List**
- **Set**
- **Map**
- **Serializable**
- **Cloneable**
- **Collection**
- **Queue**
- **SortedSet**
- **SortedMap**

## 实现类总览

- ArrayList:底层是数组

- LinkedList:底层是双向链表

- Vector:底层是数组，**线程是安全的**。效率较低，使用较少。

- HashSet:底层是HashMap，放到HashSet集合中的元素等同于放到HashMap集合Key部分。
- TreeSet：底层是HashMap，放到TreeSet集合中的元素等同于放到TreeMap集合Key部分。
- HashMap：底层是哈希表。
- Hashtable：底层是哈希表，只不过**线程安全**的。效率低。
- Properties：是线程安全的，并且key和value只能存储字符串String
- TreeMap：底层是二叉树，TreeMap集合的Key可以按照代销



List集合存储元素的特点：

- 有序可重复

Set集合存储元素的特点：

- 无序不可重复

SortedSet集合存储元素特点：

- 首先是无序不可重复的，但是SortedSet集合中的元素是可排序的

Map集合的key，就是一个Set集合

在Set集合中放数据，实际上方法放到了Map集合的key部分，value为一个object对象。

# Collection

collection是一个接口，不是类。

1. collection能存放什么类型

   没有使用泛型之前，Collection中可以存储Object的所有子类型。

   使用了“泛型”之后，Collection中只能存储某个具体的类型。

2. 常用方法：

   - add()
   - clear()
   - size()
   - contains()
   - remove()
   - toArray()
   - ...

3. **Iterator**

   在所有的Collection的实现子类中，迭代是一种通用方式，在Map当中不能使用。

   迭代器将Collection的对象都变为一个可迭代的对象。

   





##  









# System类

system类的方法全是静态方法

- 属性

  System.out  out是System类的静态变量

  ```java
  public static final InputStream in;
  public static final PrintStream out;//打印输出
  public static final PrintStream err;
  private static volatile SecurityManager security;
  private static volatile Console cons;
  private static Properties props;
  private static String lineSeparator;
  static ModuleLayer bootLayer;
  ```

- 常用方法

  System.out.println() 此方法实际上不是System的方法，而是PrintStream的方法。

  System.gc() //手动启动垃圾回收机制

  System.currentTimeMillis() //获取自1970年1月1日 00:00:00 000到当前系统时间的总毫秒数

  System.exit(0)  //退出JVM

# UML

UML是一种统一建模语言。

一种图标式语言（画图的）

只要是面向对象的编程语言，都有UML，描述类与类之间的关系，程序执行的流程

# 附录

## idea快捷方式

```bash
下面的这些常用快捷键需要在实际操作中不断地体会才能真正感受到它们的方便之处。
Ctrl + Alt + S 打开设置选项
Ctrl + / 注释 、 取消注释（单行注释）
Ctrl + Shift + / 注释 、取消注释（多行注释）
Ctrl + N 通过输入类名打开类（标准说法是查找类文件）
Ctrl + Shift + N 输入文件名打开文件（标准说法是查找文件）
Ctrl + Y 删除当前行
Ctrl + D 复制当前行到下一行
Ctrl + X 剪切当前行（也有删除当前行的作用）
Ctrl + V 粘贴
Ctrl + R 替换
Ctrl + F 在当前文件中查找 （使用 F3 在查找到的多个结果中切换）
Ctrl + Shift + F 在指定范围查找（更详细）
Ctrl + P 查看一个函数可以使用的参数 / 查看一个属性有哪些可用的值 等等
Ctrl + Shift + 1, 2, 3, 4 在某一行打标签。结合 Ctrl + 1, 2, 3, 4 可以快速定位到某一标记行。 适用于行数特别多的文件。
Ctrl + delete 删除一个连续的单位。比如，单词、连在一起的单词、连在一起的汉字等。
Ctrl + W 选择一个单位。连续按的话，会不断扩展选择的范围。
Ctrl + 左右箭头（导航键左右键） 按照单位跳转。
Ctrl + (-/+) 收起或者展开一段代码块（比如，一块注释或者一个方法太长，可以按Ctrl + - 将它收起来）
Ctrl + E 打开最近的文件
Ctrl + Shift + E 打开最近编辑过的文件
Ctrl +Tab 直接打开最近的文件
Ctrl + F12 查看当前文件的内部成员 或者说 查看本文档的结构
Ctrl + G 跳转到指定行
Ctrl + F4 关闭当前文件
Ctrl + B 打开一个类（java）的声明，其他如html jsp文件也是类似。相当于在eclipse中编辑java类文件时，按住Ctrl键并单击类名从而进入类的声明。
Ctrl + H 查看当前类的继承关系（按Shift + Esc 关闭）
Ctrl + Shift 查看一个方法的继承关系（按Shift + Esc 关闭）
Ctrl + Q 查看说明文档
Ctrl + Alt + L 格式化代码
Ctrl + Shift + I 查看类体或者方法体
Ctrl + Alt + O 自动导包
Ctrl + Alt + Enter 在当前行上面开启新的一行
Ctrl + Shift + 上下箭头 上下移动代码块（对比Alt + Shift + 上下箭头）
Ctrl + Shift + U 切换大小写
Ctrl + Shift + V 选择性粘贴（剪切板有多个选项）
Ctrl + Shift + C 复制当前文件的路径
Ctrl + Shift + J 合并下一行到当前行
Ctrl + Shift + (-/+) 折叠或者展开所有代码块
Ctrl + Shift + Space 自动提示
Alt + Enter 根据提示自动导入包
Alt + 左右箭头 在当前打卡的多个文件之间跳转
Alt + / 自动补全
Alt + 鼠标选区 可以选择方形区域（列模式）
Alt + Home 锁定到导航栏目，按导航键可以直接打开文件
Alt + 1, 2
Alt + Insert 插入构造方法、toString方法等
Alt + F7 查看方法在哪里被调用了
Shift + F6 重命名 （比如，选中名为 thread 的变量，按Shift + F6 进行重命名，可以对本类中所有原来名为thread的变量进行重命名）
Shift + Enter 在当前行下面开启新的一行
Shift + Home / End 选择光标到行首/行尾的区域
Shift + 导航箭头 自由选择
Shift + F11 查看书签
```

