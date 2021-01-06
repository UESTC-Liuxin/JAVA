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
  private static String lineSeparator;常用方法时间复杂度
  static ModuleLayer bootLayer;
  ```

- 常用方法

  System.out.println() 此方法实际上不是System的方法，而是PrintStream的方法。

  System.gc() //手动启动垃圾回收机制

  System.currentTimeMillis() //获取自1970年1月1日 00:00:00 000到当前系统时间的总毫秒数

  System.exit(0)  //退出JVM

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

## 静态内部类

静态都是用来修饰类的内部成员的。比如静态方法，[静态成员](https://www.baidu.com/s?wd=静态成员&tn=SE_PcZhidaonwhc_ngpagmjz&rsv_dl=gh_pc_zhidao)变量，静态常量。它唯一的作用就是随着类的加载（而不是随着对象的产生）而产生，以致可以用类名+[静态成员](https://www.baidu.com/s?wd=静态成员&tn=SE_PcZhidaonwhc_ngpagmjz&rsv_dl=gh_pc_zhidao)名直接获得。

static一般都只修饰内部类，不修饰外部类，修饰内部类的时候，称之为静态内部类：不需要依赖**外部类**；不能使用外部类的非静态属性和方法。

静态内部类实例：

```java
public class StaticInnerClassTest {

    public static void main(String[] args) {
        Student.School.name="nankai";

    }
}

class Student{
    public static class School{
        public static String name;
        public static String address;
    }

}
```

## 静态内部类的作用

> 在一个类中创建另外一个类，叫做成员内部类。这个成员内部类可以静态的（利用static关键字修饰），也可以是非静态的。由于静态的内部类在定义、使用的时候会有种种的限制。所以在实际工作中用到的并不多。 
> 　　在开发过程中，内部类中使用的最多的还是非静态地成员内部类。不过在特定的情况下，静态内部类也能够发挥其独特的作用。 
>
> 　　一、静态内部类的使用目的。 
>
> 　　在定义内部类的时候，可以在其前面加上一个权限修饰符static。此时这个内部类就变为了静态内部类。不过由于种种的原因，如使用上的限制等等因素(具体的使用限制，笔者在下面的内容中会详细阐述)，在实际工作中用的并不是很多。但是并不是说其没有价值。在某些特殊的情况下，少了这个静态内部类还真是不行。如在进行代码程序测试的时候，如果在每一个Java源文件中都设置一个主方法(主方法是某个应用程序的入口，必须具有)，那么会出现很多额外的代码。而且最主要的时这段主程序的代码对于Java文件来说，只是一个形式，其本身并不需要这种主方法。但是少了这个主方法又是万万不行的。在这种情况下，就可以将主方法写入到静态内部类中，从而不用为每个Java源文件都设置一个类似的主方法。这对于代码测试是非常有用的。在一些中大型的应用程序开发中，则是一个常用的技术手段。为此，这个静态内部类虽然不怎么常用，但是程序开发人员还必须要掌握它。也许在某个关键的时刻，其还可以发挥巨大的作用也说不定。 
>
> 　　二、静态内部类的使用限制。 
>
> 　　将某个内部类定义为静态类，跟将其他类定义为静态类的方法基本相同，引用规则也基本一致。不过其细节方面仍然有很大的不同。具体来说，主要有如下几个地方要引起各位程序开发人员的注意。 
>
> 　　一是静态成员(包括静态变量与静态成员)的定义。一般情况下，如果一个内部类不是被定义成静态内部类，那么在定义成员变量或者成员方法的时候，是不能够被定义成静态成员变量与静态成员方法的。也就是说，在非静态内部类中不可以声明静态成员。如现在在一个student类中定义了一个内部类age，如果没有将这个类利用static关键字修饰，即没有定义为静态类，那么在这个内部类中如果要利用static关键字来修饰某个成员方法或者成员变量是不允许的。在编译的时候就通不过。故程序开发人员需要注意，只有将某个内部类修饰为静态类，然后才能够在这个类中定义静态的成员变量与成员方法。这是静态内部类都有的一个特性。也正是因为这个原因，有时候少了这个静态的内部类，很多工作就无法完成。或者说要绕一个大圈才能够实现某个用户的需求。这也是静态的内部类之所以要存在的一个重要原因。 
>
> 　　二是在成员的引用上，有比较大的限制。一般的非静态内部类，可以随意的访问外部类中的成员变量与成员方法。即使这些成员方法被修饰为private(私有的成员变量或者方法)，其非静态内部类都可以随意的访问。则是非静态内部类的特权。因为在其他类中是无法访问被定义为私有的成员变量或则方法。但是如果一个内部类被定义为静态的，那么在银用外部类的成员方法或则成员变量的时候，就会有诸多的限制。如不能够从静态内部类的对象中访问外部类的非静态成员(包括成员变量与成员方法)。这是什么意思呢?如果在外部类中定义了两个变量，一个是非静态的变量，一个是静态的变量。那么在静态内部类中，无论在成员方法内部还是在其他地方，都只能够引用外部类中的静态的变量，而不能够访问非静态的变量。在静态内部类中，可以定义静态的方法(也只有在静态的内部类中可以定义静态的方法)，在静态方法中引用外部类的成员。但是无论在内部类的什么地方引用，有一个共同点，即都只能够引用外部类中的静态成员方法或者成员变量。对于那些非静态的成员变量与成员方法，在静态内部类中是无法访问的。这就是静态内部类的最大使用限制。在普通的非静态内部类中是没有这个限制的。也正是这个原因，决定了静态内部类只应用在一些特定的场合。其应用范围远远没有像非静态的内部类那样广泛。 
>
> 　三是在创建静态内部类时不需要将静态内部类的实例绑定在外部类的实例上。 
>
>    通常情况下，在一个类中创建成员内部类的时候，有一个强制性的规定，即内部类的实例一定要绑定在外部类的实例中。也就是说，在创建内部类之前要先在外部类中要利用new关键字来创建这个内部类的对象。如此的话如果从外部类中初始化一个内部类对象，那么内部类对象就会绑定在外部类对象上。也就是说，普通非静态内部类的对象是依附在外部类对象之中的。但是，如果成员开发人员创建的时静态内部类，那么这就又另当别论了。通常情况下，程序员在定义静态内部类的时候，是不需要定义绑定在外部类的实例上的。也就是说，要在一个外部类中定义一个静态的内部类，不需要利用关键字new来创建内部类的实例。即在创建静态类内部对象时，不需要其外部类的对象。具体为什么会这样，一般程序开发人员不需要了解这么深入，只需要记住有这个规则即可。在定义静态内部类的时候，千万不要犯画蛇添足的错误。 
>
> 　　从以上的分析中可以看出，静态内部类与非静态的内部类还是有很大的不同的。一般程序开发人员可以这么理解，非晶态的内部类对象隐式地在外部类中保存了一个引用，指向创建它的外部类对象。**不管怎么理解，程序开发人员都需要牢记静态内部类与非静态内部类的差异。如是否可以创建静态的成员方法与成员变量(静态内部类可以创建静态的成员而非静态的内部类不可以)、对于访问外部类的成员的限制(静态内部类只可以访问外部类中的静态成员变量与成员方法而非静态的内部类即可以访问静态的也可以访问非静态的外部类成员方法与成员变量)。这两个差异是静态内部类与非静态外部类最大的差异，也是静态内部类之所以存在的原因。了解了这个差异之后，程序开发人员还需要知道，在什么情况下该使用静态内部类。如在程序测试的时候，为了避免在各个Java源文件中书写主方法的代码，可以将主方法写入到静态内部类中，以减少代码的书写量，让代码更加的简洁。** 
>
> 　　总之，静态内部类在Java语言中是一个很特殊的类，跟普通的静态类以及非静态的内部类都有很大的差异。作为程序开发人员，必须要知道他们之间的差异，并在实际工作中在合适的地方采用合适的类。不过总的来说，静态内部类的使用频率并不是很高。但是在有一些场合，如果没有这个内部静态类的话，可能会起到事倍功半的反面效果

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

- **方法重写不能比原方法抛出更多异常hash**



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

- ArrayList:底层是数组，ArrayList常用方法时间复杂度
- LinkedList:底层是双向链表
- Vector:底层是数组，**线程是安全的**。效率较低，使用较少。
- HashSet:底层是HashMap，放到HashSet集合中的元素等同于放到HashMap集合Key部分。
- TreeSet：底层是HashMap，放到TreeSet集合中的元素等同于放到TreeMap集合Key部分。
- HashMap：底层是哈希表。
- Hashtable：底层是哈希表，只不过**线程安全**的。效率低。
- Properties：是**线程安全**的，并且key和value只能存储字符串String
- TreeMap：底层是二叉树，TreeMap集合的Key可以按照代销



List集合存储元素的特点：

- 有序可重复

Set集合存储元素的特点：

- 无序不可重复

SortedSet集合存储元素特点：

- 首先是无序不可重复的，但是SortedSet集合中的元素是可排序的

==Map集合的key，就是一个Set集合，在Set集合中放数据，实际上方法放到了Map集合的key部分，value为一个object对象。==

## 实现类操作复杂度

### ArrayList

ArrayList底层数据结构是：**数组**

**增加**

add(E)：尾部添加，时间复杂度**O(1)**

add(index, E)： 指定位置添加，时间复杂度**O(N)**;指定位置添加后，需要将指定位置后面的全部元素向后移动一个位置

**删除**

remove(E) ：删除指定元素，需要先遍历找到指定元素，因此时间复杂度是**O(N)**

remove(index)：删除指定位置元素，删除后需要将指定位置后面的元素全部向前移动一个位置，时间复杂度**O(N)**

**查询**

get(index)：查询指定位置元素，**O(1)**

**改**

set(index, E)：修改指定位置元素，**O(1)**

**contains()方法**

需要遍历数据去判断是否存在指定元素，所以是**O(N)**

### HashMap

HashMap底层数据结构是：**数组+链表+红黑树**

增删查改时间复杂度均为**O(1)**

containsKey()方法时间复杂度是**O(1)**

containsValue()方法时间复杂度是**O(N)**

### TreeMap

TreeMap底层数据结构是：**红黑树**

增删查改时间复杂度均为**O(logN)**

containsKey()时间复杂度**O(logN)**

containsValue()时间复杂度**O(N)**，因为value是无序的，所以要依次遍历

### LinkedHashMap

LinkedHashMap底层数据结构是：**HashMap+双向链表**

增删查改时间复杂度均为**O(1)**

containsKey()时间复杂度是**O(1)**

containsValue()时间复杂度是**O(N)**

### HashSet

HashSet底层数据结构是基于**HashMap**来实现的

因此增删查改时间复杂度均为**O(1)**

contains()方法时间复杂度**O(1)**

### TreeSet

TreeSet底层数据结构是基于**TreeMap**来实现的

增删查改时间复杂度均为**O(logN)**

contains()时间复杂度**O(logN)**

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
   
   ==注意：集合的结构发生改变时，迭代器必须重新获取，如果还是用以前老的迭代器，会出现异常。==
   
   比如：
   
   ```java
   public class CollectionTest2 {
       public static void main(String[] args) {
           Collection c = new HashSet();
           c.add(new String("aa"));
           c.add(new String("bb"));
           c.add(100);
           c.add(new Object());
   
           Iterator it = c.iterator();
           c.remove(100);//发生结构改变
           while(it.hasNext()){
               System.out.println(it.next());//
           }
       }
   }
   ```
   
   这里会报出异常：
   
   ```bash
   Exception in thread "main" java.util.ConcurrentModificationException
   	at java.base/java.util.HashMap$HashIterator.nextNode(HashMap.java:1493)
   	at java.base/java.util.HashMap$KeyIterator.next(HashMap.java:1516)
   	at com.javase.collection.CollectionTest2.main(CollectionTest2.java:23)
   ```
   
   但是可以调用迭代器当中的remove操作进行删除，会将迭代器当中的元素与源集合的元素一起删掉。==但是it必须要调用next()方法后才能进行删除：具体的原因见==[[Java集合 iterator.remove()方法详解](https://www.cnblogs.com/qsymg/p/9847762.html)](https://www.cnblogs.com/qsymg/p/9847762.html)
   
   ```java
   public class CollectionTest2 {
       public static void main(String[] args) {
           Collection c = new HashSet();
           c.add(new String("aa"));
           c.add(new String("bb"));
           c.add(100);
           c.add(new Object());
   
           Iterator it = c.iterator();
           while(it.hasNext()){
               it.next();//
               it.remove();
           }
           System.out.println(c.size());//0
       }
   }
   ```
   
   
   
4. **contains方法**

   判断集合中是否包含某元素。

   ```java
   public class ContatinsTest {
       public static void main(String[] args) {
           Collection c= new ArrayList();
   
           c.add(new String("abc"));
           c.add(new String("def"));
   
           c.add(new Object());
   
           System.out.println(c.contains(new String("abc")));//ture
           System.out.println(c.contains(new Object()));//false
       }
   }
   ```

虽然在ArraList中的数组当中没有包含新创建的String对象，但是在底层的contains的代码中：

```java
int indexOfRange(Object o, int start, int end) {
    Object[] es = this.elementData;
    int i;
    if (o == null) {
        for(i = start; i < end; ++i) {
            if (es[i] == null) {
                return i;
            }
        }
    } else {
        for(i = start; i < end; ++i) {
            if (o.equals(es[i])) {
                return i;
            }
        }
    }

    return -1;
}
```

这里调用了的是equals方法，由于String对象的equals方法是比较的内容，也就是“abc”，所以这里为true。

==所以利用Collection的时候一定要记得重写equals方法。==

5. **remove方法**

   ```java
   public class RemoveTest {
       public static void main(String[] args) {
           Collection c=new ArrayList();
   
           String s1= new String("abc");
           String s2= new String("abc");
   
           c.add(s1);
           c.remove(s2);
           System.out.println(c.size());//0
       }
   }
   ```

   同样的，remove方法也调用的是equals方法进行比较。

# Collections工具

集合工具类，方便集合的操作。

```java
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

```



# List

1. List集合存储元素特点：有序可重复

   有序：List集合中的元素有下标

   从0开始，以1递增

   可重复：存储一个1，还可以再存储1。

2. List的所有元素都是可以用过下标访问的，但是底层的实现不一样

   ArrayList的底层实现是数组；LInkedList的底层实现是双向链表...

### ArrayList

ArrayList继承自AbstractList，是List的实现类，底层实现代码是数组。

在低版本的java当中，ArrayList的初始化容量是10，至少8.0之后，初始化的容量为1，在添加元素的时候进行扩容，当超出当前容量时，会进行1.5倍扩容，==第一次扩容的容量是10==。

ArrayList是非线程安全的。

```java
    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = this.grow();
        }

        elementData[s] = e;
        this.size = s + 1;
    }
```

在ArrayList的构造方法中，可以利用Collection集合进行初始化。

```java
        Collection c = new HashSet();
        c.add(1);
        c.add(2);

        List list3 = new ArrayList(c);
        System.out.println(list3.size());//2
```

### LinkedList

**底层实现是双向链表**，但是同样是有下标的。但是查找效率很低。但是随机增删的效率很高。

在java的实现中，LinkedList对于第一次添加元素的时候，将一个null赋给了节点的pre。

### vector

**vector的底层实现是数组**，初始容量为10，每次扩容为双倍。vetcor是线程安全的，但是效率很低，很少使用。

# Set

## HashSet

## TreeSet

按照字典序排序：

```java
public class TreeSetTest {
    public static void main(String[] args) {
        Set<String> set = new TreeSet();
        set.add("liuxin");
        set.add("liusiyu");java
        set.add("zhangyijie");

        for(String s:set){
            System.out.println(s);
        }
        //        liusiyu
        //        liuxin
        //        zhangyijie
    }
}
```

TreeSet无法对自定义的类进行排序。比如以下代码：

```java
public class TreeSetTest01 {
    public static void main(String[] args) {
        Person p0 = new Person(10);
        Person p1 = new Person(15);
        Person p2 = new Person(5);

        TreeSet<Person> set = new TreeSet<>();
        set.add(p0);
        set.add(p1);
        set.add(p2);

        for(Person p:set){
            System.out.println(p);
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
                '}';java
    }
}
```

报错:

```bash
Exception in thread "main" java.lang.ClassCastException: class com.javase.Map.Person cannot be cast to class java.lang.Comparable (com.javase.Map.Person is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap')
	at java.base/java.util.TreeMap.compare(TreeMap.java:1291)
	at java.base/java.util.TreeMap.put(TreeMap.java:536)
	at java.base/java.util.TreeSet.add(TreeSet.java:255)
	at com.javase.Map.TreeSetTest01.main(TreeSetTest01.java:12)
```

因此在TreeSet储存的数据类型，是需要重写compareTo函数的。

```java
public class TreeSetTest01 {
    public static void main(String[] args) {
        Person p0 = new Person(10);
        Person p1 = new Person( 15);
        Person p2 = new Person(5);

        TreeSet<Person> set = new TreeSet<>();
        set.add(p0);
        set.add(p1);
        set.add(p2);

        for(Person p:set){
            System.out.println(p);
        }
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

    @Override
    public int compareTo(Person person) {
        return this.age-person.age;
    }

}
```

## 自平衡二叉树数据结构

1. TreeSet/TreeMap是自平衡二叉树，遵循左小右大原则存放。

2. 遍历二叉树的时候有三种方式：按照根的位置分为：前中后三种。

3. TreeSet集合/TreeMap集合采用的是：中序遍历方式。

   Iteration迭代采用的是中序遍历方式。

4. 老杜只讲到了平衡二叉树，没有讲到自平衡的实现的时候。

5. 。。。。。。。。

## Comparator

除了利用接口实现的方式以外，还有利用比较器实现的方式，两种方式对比：

```java
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
```



# Map

- Map和Collection没有继承关系；

- Map集合以key的方式储存数据：键值对；
  - key和value都是引用数据类型；
  - key和value都是储存对象的内存地址
  - key起到主导的地位，value是key的一个附属品

- Map常用方法：

  V put（K key V value）向Map集合中添加键值对

  void clear() 清空Map集合

  boolean containsKey(Object key)

  boolean contatinsValue(Object value)

  V get(Object key)

  boolean isEmpty()

  Set<K> keySet()

  

  Collection<V> values()

  Set<Map.Entry<K,V>> entrySet()

  将Map集合转成Set集合，集合当中的元素类型为Map.Entry<K,V>，其为Map的一个**静态内部类**，包含K和V两种泛型。

  利用其遍历：

  ```java
  public class MapTest01 {
      public static void main(String[] args) {
          Map<Integer,String> map =new HashMap<>();
          map.put(1,"Liu");
          map.put(2,"liang");
  
  
          Set<Map.Entry<Integer,String>> set = map.entrySet();
          for (Map.Entry entry : set){
              System.out.println(entry);
          }
      }
  
  }
  ```

## HashMap

HashMap是数组+单线链表+（红黑树）的结构。

哈希表将数组和链表的优势结合到一起。

```java
public class HashMap{
	Node<K,V> table;
	static class Node<K,V>{//静态内部类	
	}
}
```

 其中，最重要的就是这个静态内部类Node

```java
    static class Node<K, V> implements Entry<K, V> {
        final int hash; //记录的是key的哈希值，可通过哈希算法转换为数组下标
        final K key;
        V value;
        HashMap.Node<K, V> next;  

        Node(int hash, K key, V value, HashMap.Node<K, V> next) {//
            this.hash = hash; 
            this.key = key; 
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
        }

        public final V getValue() {
        }

        public final String toString() {
        }

        public final int hashCode() {
        }

        public final V setValue(V newValue) {
        }

        public final boolean equals(Object o) {
            }
        }
    }
```

**map.put(k,v)的实现原理**：

第一步：先将k,v封装到Node对象当中；

第二部：底层会调用k的hashCode()方法得出hash值，然后通过hash函数/哈希算法，将hash值转换成数组的小标，下标位置上如果没有任何元素，就把Node添加到这个位置上。如果说下标对应的位置上有链表，此时会拿着k和链表上的每一个结点进行equals，如果所有的equals方法都返回fasle，那么新结点将被添加到链表的末尾。如果其中一个equals返回了true，就替换为新的value。

**map.get(k)实现原理**

调用hashcode()得出hash值，转为下标，再顺着链表寻找，没找到就返回null。

==放在HashMap集合Key部分的元素，以及放在HashSet集合中的元素，需要同时重写**hashCode方法**和**equals**方法。重写的目的就是把地址的哈希值换成value的hash值，不然hash值永远都不同。==

比如String重写的hashCode：

```java
    public int hashCode() {
        int h = this.hash;
        if (h == 0 && this.value.length > 0) {
            this.hash = h = this.isLatin1() ? StringLatin1.hashCode(this.value) : StringUTF16.hashCode(this.value);
        }

        return h;
    }
```

**HashMap的初始容量为16，默认加载因子是0.75（当储存到了0.75*当前最大容量的时候就进行扩容）**

hashcode和equals重写实现：

```java
class Student{
    private String name;
    private int age;

    Student(String name) {
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getName(), student.getName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
```

**红黑树机制**（jdk8之后推出的新特性）

当链表的长度超过8的时候，会将链表构建成二叉树结构（红黑树），当小于二叉树的结点小于6的时候，又变成链表。

```java
static final int TREEIFY_THRESHOLD = 8;
static final int UNTREEIFY_THRESHOLD = 6;
```

## HashMap与HashTable

HashTable方法都带有synchronized：线程安全的。线程安全有其他的方案，这个Hashtable对线程的处理导致效率较低，使用较少了。

**关于key为空值**

==在HashMap里是key是可以为空值的，但是在HashTable里，key为空会导致空指针异常。==

## Properties

Propertise是一个Map集合，继承Hashtable，Properties的key和value都是String类型。

Propreties被称为属性类对象。

一共就两个方法。

```java
public class properties {
    public static void main(String[] args) {
        //创建一个Properties对象
        Properties pro = new Properties();
        pro.setProperty("url","www.baidu.com");
        pro.setProperty("username","root");

        String url = pro.getProperty("url");
        String username = pro.getProperty("username");

        System.out.println(url);
        System.out.println(username);
    }
}
```



# 泛型机制

jdk5.0之后推出泛型。==泛型是属于编译期间的一个特性==。

对于此段代码：

```java
public class GenericTest {
    public static void main(String[] args) {

        List l = new ArrayList();

        Animal a = new Cat();
        Animal b = new Bird();

        l.add(a);
        l.add(b);

        Iterator it = l.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if(obj instanceof Animal){ //略显臃肿
                ((Animal) obj).move();
            }
        }
    }
}

class Animal{
    public void move(){
        System.out.println("Animal moving...");
    }
}

class Cat extends Animal{
    @Override
    public void move() {
        System.out.println("Cat runing...");
    }

    public void catchMouse(){
        System.out.println("Catching mouses...");
    }

}
class Bird extends Animal{
    @Override
    public void move() {
        System.out.println("Bird flying...");
    }

    public void fly(){
        System.out.println("Flying....");
    }
}

```

在取出对象的时候略显臃肿。

可将相关类型都限定为Animal，其代码如下:

```java
public static void main(String[] args) {

    List<Animal> l = new ArrayList();

    Animal a = new Cat();
    Animal b = new Bird();

    l.add(a);
    l.add(b);

    Iterator<Animal> it = l.iterator();
    while (it.hasNext()) {
        Animal animal = it.next();
        animal.move();
    }
}
```

## 泛型的好处：

- 集合中元素的类型统一了。
- 从集合中取出的元素类型是反省制定的类型，不需要进行大量的向下转型操作。

## 泛型的缺点：

- 使用泛型之后，集合中的元素的数据类型更加统一了。

## 自动类型推断机制

在jdk8之后引入了自动类型推断机制。又称为砖石表达式。

可以省略ArrayList的Animal。

```java
List<Animal> l = new ArrayList();
```

##　自定义泛型

原本泛型的E实际上就是一个标识符，可以被替换为任何其他合法标识符。

```java
public class GenericTest01<标识符随便写> {
    public static void main(String[] args) {
        //new对象的时候指定了泛型是：String类型
        GenericTest01<String> gt = new GenericTest01<>();
        gt.doSome("abc");

        GenericTest01<Integer> gt1 = new GenericTest01<>();
        gt1.doSome(123);
    }

    public void doSome(标识符随便写 o){
    }
}
```

# IO

## IO的分类：

- 一种方式是按照流的方向进行分类：以内存作为参照物，往内存中去，叫做输入（Input），或者叫做读；从内存中出来，叫做输出（Write）。或者叫做写。

- 另一种方式是按照读取数据方式不同进行分类：

  - 有的流是按照字节的方式读取数据，一次读取一个字节byte，等同于一次读取8个二进制位。这种流是万能的，什么类型的文件都可以读取。包括：文本文件，图片，声音文件等多媒体文件。

  - 有的流是按照字符的方式读取数据的，一次读取一个字符，这种流是为了方便读取普通文本文件而存在的，这种流不能读取：图片、声音、视频等文件。只能读取纯文本文件，连word文件都无法读取。
  
    ==具体区别在于:按照字符读取，首先要识别为特定字符，比如汉字，会识别为汉字进行二个字节读取，英文字母识别为一个字节读取。==

## java IO流这块的四大家族

- java.io.InputStream  字节输入流

- java.io.OutputStream  字节输出流

- java.io.Reader   字符输入流

- java.io.Writer  字符输出流
- 四大家族的首领都是抽象类。（abstract class）
- 所有的流都实现了：java.io.Closeable接口，都是可关闭的，都有close()方法。流毕竟是一个管道，这个是内存和硬盘之间的通道，用完之后一定要关闭。不然会耗费（占用）很多资源。养成好习惯，用完流要关闭。
- 所有的流都实现了：java.io.Flushable接口，都是可刷新的，都有flush()方法。养成一个好习惯，输出流在最终输出之后，一定要记得flush()。刷新一下，这个刷新表示通道/管道当中剩余未输出的数据，强行输出完（清空管道！）刷新的作用就是清空管道。如果没有flush()可能会导致丢失数据。

==注意：在java当中只要“类名”以Stream结尾的都是字节流。以Reader结尾的都是字符流==。

![image-20201231181333730](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201231181333730.png)

所有的**OutputStream都继承于OutputStream抽象类

## FileInputStram

字节流数据。

```java
public class FileInputStreamTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try{
            //创建文件字节输入流对象
            //文件路径："/media/Program/JAVA/programs"
            //以下都是采用了：绝对路径的方式
            //FileInputStream
            fis = new FileInputStream("/media/Program/JAVA/programs/read_write_test");

            //开始读
            int readData = fis.read();
            System.out.println(readData);//97

            readData = fis.read();
            System.out.println(readData);//98

            readData = fis.read();
            System.out.println(readData);//99

            readData = fis.read();
            System.out.println(readData);//100

            readData = fis.read();
            System.out.println(readData);//101

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
```

将数据读入Byte数组里，注意：==每一次读取的游标会被记录，如果剩余字符不足以填充byte数组的话，也不再继续读取。需要注意的是，虽然第二次读取没有读取到8个字节，但是由于储存的方式是覆盖式储存，因此后面几个位置的byte数组会保留上一次读取的结果。==

```java
package com.javase.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest01 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try{
            //创建文件字节输入流对象
            //文件路径："/media/Program/JAVA/programs"
            //以下都是采用了：绝对路径的方式
            //FileInputStream

            //工程目录在Programs
            fis = new FileInputStream("JavaNote/src/com/javase/io/read_write_test");

            //开始读，采用byte数组，一次读取多个字节。最多读取“数组.length个字节”
            byte[] bytes = new byte[8];
            int readCount = fis.read(bytes);
            System.out.println(readCount);
            System.out.println(new String(bytes));//abcdefgh

            readCount = fis.read(bytes);
            System.out.println(readCount);
            System.out.println(new String(bytes));//ijkdefgh   这里重复了

            readCount = fis.read(bytes);
            System.out.println(readCount);
            System.out.println(new String(bytes));//ijkdefgh


        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
}

```

根据上述代码可优化为：

```java
package com.javase.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest02 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try{
            //创建文件字节输入流对象
            //文件路径："/media/Program/JAVA/programs"
            //以下都是采用了：绝对路径的方式
            //FileInputStream

            //工程目录在Programs
            fis = new FileInputStream("JavaNote/src/com/javase/io/read_write_test");

            //开始读，采用byte数组，一次读取多个字节。最多读取“数组.length个字节”
            byte[] bytes = new byte[8];
            int readCount = 0;

            while((readCount=fis.read(bytes))!=-1) {
                System.out.println(new String(bytes,0,readCount));//abcdefgh
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
}

```

可以利用**available()**方法动态初始化数组大小。

```java
byte[] bytes = new byte[fis.available()];

int readCount=fis.read(bytes);
System.out.println(new String(bytes));
```

可用skip()进行读取跳跃。

## FileOutputStream

```java
public class FileOutputStreamTest01 {
    public static void main(String[] args) {
        FileOutputStream fos=null;
        try{
            //没有文件会新建，但是不能递归创建文件夹，每次写入会覆盖(可将append参数设置为true)
            fos = new FileOutputStream("JavaNote/src/com/javase/io/write_test",true);
            byte[] bytes = new byte[]{97,98,99,100};
            //开始写
            fos.write(bytes);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

文件复制的演示：

```java
public class CopyTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream("JavaNote/src/com/javase/io/write_test");
            fos = new FileOutputStream("JavaNote/src/com/javase/io/write_test01");

            //最核心的：一边读，一边写
            byte[] bytes = new byte[20];

            int readCount=0;
            while ((readCount=fis.read(bytes))!=-1){
                fos.write(bytes,0,readCount);
            }
            //刷新，输出流最后要刷新
            fos.flush();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            //分开 try，不要一起try
            //一起try的时候，可能会造成另一个流无法正常关闭
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

## FileReader和FileWriter

```java
public class FileReaderTest {
    public static void main(String[] args) {
        FileReader reader=null;
        try {
            reader = new FileReader("JavaNote/src/com/javase/io/write_test");
            char[] chars = new char[4];
            reader.read(chars);

            System.out.println(new String(chars));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

## BufferedReader和BufferedWriter

带有缓冲区的字符输入流，使用这个流的时候不需要自定义char数组，或者说不需要自定义byte数组。自带缓冲。

两个基本概念：

- 节点流：当一个流的构造方法中需要一个流的时候，这个被传进来的流叫做：节点流。
- 包装流：外部负责包装的这个流，叫做：包装流，还有一个名字叫做：包装流。

**节点流和包装流的概念是相对的**

```java
package com.javase.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {

        //定义节点流
        FileReader reader = new FileReader("JavaNote/src/com/javase/io/write_test");

        //定义包装流
        BufferedReader br = new BufferedReader(reader);

        String s=null;
        while ((s= br.readLine())!= null){
            System.out.println(s);
        }

        //关闭流
        br.close();
    }
}
```

可以利用转换流将字节流转换为字符流

```java
public class BufferedReaderTest01 {
    public static void main(String[] args) throws IOException {

        //字节流
        FileInputStream in = new FileInputStream("JavaNote/src/com/javase/io/write_test");
        //通过转换流转换
        InputStreamReader reader = new InputStreamReader(in);
        //这个构造方法只能传一个字符流，不能传字节流
        BufferedReader br = new BufferedReader(reader);

        System.out.println(br.readLine());
    }
}
```

## DataOutputStream与DataIntputStream

读和取的顺序必须一样。

```java
public class DataOutputStreamTest {
    public static void main(String[] args) throws Exception{
        //创建数据专属的字节输出
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data"));

        //写数据
        byte b = 100;
        short t = 200;
        float f = 1.62F;
        long l = 1522;
        double d = 0.36;
        boolean sex = true;
        char c = 'a';

        dos.writeByte(b);
        dos.writeShort(t);
        dos.writeFloat(f);
        dos.writeLong(l);
        dos.writeDouble(d);
        dos.writeBoolean(sex);
        dos.writeChar(c);

        //刷新
        dos.flush();
        //关闭最外层
        dos.close();

        DataInputStream dis = new DataInputStream(new FileInputStream("data"));
        //写数据
        b = dis.readByte();
        t = dis.readShort();
        f = dis.readFloat();
        l = dis.readLong();
        d = dis.readDouble();
        sex = dis.readBoolean();
        c = dis.readChar();
    }

}

```

## 标准输出流

PrintStream为另一个输出流添加了功能，即方便地打印各种数据值的表示的能力。另外还提供了两个特性。与其他输出流不同，PrintStream从不抛出IOException；相反，异常情况只是设置一个可以通过checkError方法测试的内部标志。可选地，可以创建PrintStream以便自动刷新；这意味着在写入字节数组、调用println方法之一或写入换行符或字节（'\n'）后自动调用flush方法。

以下代码是等同的：

```java
public class PrintStreamTest {
    public static void main(String[] args) {
        System.out.println("hello world");

        PrintStream ps = System.out;
        ps.println("hello world");
    }
}
```

同时可以将System.out是System里的一个属性，指向了控制台。

可以修改系统输出的指向：

```java
        PrintStream printStream = new PrintStream(new FileOutputStream("log"));
        System.setOut(printStream);
        System.out.println(new Date()); //像日志文件输出日期
```

## File

1. File类不属于IO流四大家族，因此不能直接进行读取操作

2. File类是对文件（或者文件路径）的抽象表示形式。

3. File类常用方法：

   ```java
   package com.javase.io;
   
   import java.io.File;
   import java.io.IOException;
   
   public class FileTest {
       public static void main(String[] args) throws IOException {
           File f1 = new File("/media/Program/JAVA/programs/log2/test");
   
           //判断文件是否存在
           System.out.println(f1.exists());
   
           //创建新文件，需要处理IO异常
           if(!f1.exists()){
   //            f1.createNewFile();
               //创建为文件夹
               f1.mkdir();
               //递归创建文件夹
               f1.mkdirs();
               //获取上层路径
               System.out.println(f1.getParent()); ///media/Program/JAVA/programs/log2
           }
    
   ```

   list文件或者文件路径：

   ```java
   public class FileTest02 {
       public static void main(String[] args) {
           File f2 = new File("/media/Program/JAVA/programs/JavaNote/src");
           //或者用list(获取所有文件以及文件目录)
           File[] files = f2.listFiles();
           for(File f:files){
               System.out.println(f.getName());
           }
       }
   }
   ```

   练习：递归复制文件夹

   ```java
   public class copyDirTest {
       public static void main(String[] args) {
           File src = new File("/media/Program/JAVA/programs/copied");
           File dest = new File("/media/Program/JAVA/programs/copyTo");
           copyFilesDirs(src,dest);
   
       }
   
       /**
        * 将指定文件拷贝至指定文件夹下
        * @param src
        * @param dest
        */
       static void copyFilesDirs(File src,File dest){
           if(!src.exists()) return;
           //如果File是文件，直接拷贝
           if(src.isFile()){
               copy(src,dest);
           }
           else{
               File[] files = src.listFiles();
               for(File file: files) {
                   String fileDir = file.getAbsolutePath();
                   String[] fileDirSplits = fileDir.split("/");
                   String lastSrcDir = fileDirSplits[fileDirSplits.length - 1];
                   String destDir = dest.getAbsolutePath().endsWith("/") ? dest.getAbsolutePath() : dest.getAbsolutePath() + "/"
                           + lastSrcDir;
   
                   File destFile = new File(destDir);
                   //创建相关目录
                   if(file.isDirectory()) {
                       if (!destFile.exists()) {
                           destFile.mkdirs();
                       }
                   }
                   copyFilesDirs(file,destFile);
               }
           }
       }
   
       static void copy(File src,File dest){
           FileInputStream fis = null;
           FileOutputStream fos = null;
   
           try {
               fis = new FileInputStream(src.getAbsoluteFile());
               fos = new FileOutputStream(dest.getAbsoluteFile());
   
               //最核心的：一边读，一边写
               byte[] bytes = new byte[20];
   
               int readCount=0;
               while ((readCount=fis.read(bytes))!=-1){
                   fos.write(bytes,0,readCount);
               }
               //刷新，输出流最后要刷新
               fos.flush();
   
   
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }catch (IOException e){
               e.printStackTrace();
           } finally {
               //分开 try，不要一起try
               //一起try的时候，可能会造成另一个流无法正常关闭
               if(fis != null){
                   try {
                       fis.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
               if(fos != null){
                   try {
                       fos.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
       }
   }
   ```

## 序列化与反序列化

序列化：Serialize java对象存储到文件中。将java对象的状态保存下来的过程

反序列化:DeSerialize  将硬盘上的数据重新恢复到内存当中，恢复成java对象。

**ObjectInputStream，ObjectOutputStream**

参与序列化和反序列化的对象必须实现Serializable。（Serializable接口没有任何代码，起到标识符作用，用以给java虚拟机做参考的，java虚拟机看到此接口后，会为该类自动生成一个序列化版本号。）

序列化版本号用于区分同一个类名的不同类。自动生成序列化版本号缺点是：一旦代码确定之后，不能进行后续的修改，java虚拟机先通过类名，再通过版本序列号进行识别。可以手动指定。并且代码修改不影响反序列化。

序列化与反序列：（多个对象可利用List实现）

```java
public class Serialize_DeserializeTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students"));
        oos.writeObject(new Student("liuxin",24));
        oos.flush();
        oos.close();
        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students"));
        Object Obj = ois.readObject();
        ois.close();
        System.out.println(Obj); //Student{name='liuxin', age=24}
    }
}
```

**transient关键字**（游离的，不参与序列化操作）

==注意：如果成员变量是一个引用数据类型，此引用类型也需要对Serialize进行实现。==

## IO+Properties

```java
public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        /**
         * Properties是一个Map集合，key和value都是String类型。
         * 想将userinfo文件中的数据加载到Propertise对象当中。
         */
        //新建一个输入流对象
        FileInputStream fis = new FileInputStream("./JavaNote/userinfo");
        Properties pro = new Properties();
        pro.load(fis);
        fis.close();
        System.out.println(pro);//{password=123456, user=liuxin}
    }
}
```

java规范中：属性配置文件建议以properties结尾，但这不是必须的。’#‘是注释。

# UML

UML是一种统一建模语言。

一种图标式语言（画图的）

只要是面向对象的编程语言，都有UML，描述类与类之间的关系，程序执行的流程

# 多线程

1. 什么是进程？什么是线程？

   进程是一个应用程序。

   线程是一个进程中执行场景/执行单元

   一个进程可以启动多个线程。

2. 对于java程序来说，当在dos命令窗口中输入：`java HellWorld`

   回车之后。会先启动JVM，而JVM就是一个进程。

   JVM再启动一个主线程调用main方法。同时再启动一个来及回收线程负责看护，回收垃圾。最起码，现在的java程序中至少有两个线程并发。一个是垃圾回收线程，一个是指向main方法的主线程。

3. 进程和线程的区别

   进程之间内存独立不共享。

   线程之间（在java语言当中）：**堆内存和方法去共享，但是栈内存独立，一个线程一个栈**。

   启动多个线程，线程之间会有多个栈空间，每个栈和每个栈之间互不干扰，各自执行各自的，这就是多线程并发。

4. 主线程（或者说main函数）结束并不会导致程序结束，还会有线程在压栈弹栈。

5. 对于单核的cpu来说，不能做到真正的多线程并发。真正的多线程保证各个线程在同一个时间点都在互不干扰的执行。多核才可以。

## 实现多线程的方式

**方式一：直接继承Thread方法**

```java
package com.javase.threads;

public class ThreadTest01 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        thread1.start();

        for(int i=0;i<1000;i++)
            System.out.println("主线程"+i);
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        //编写程序，这段程序的代码属于子线程，在子栈中运行
        for(int i=0;i<1000;i++)
            System.out.println("分支线程"+i);

    }
}
```

==start()方法的作用是：启动一个分支线程，在JVM当中**开辟一个新的栈空间**，这段代码任务完成之后，瞬间就结束了。启动成功的线程会自动调用run方法，并且run方法在分支栈的栈底。不能直接掉run方法（否则是单线程）。==

**方式二：实现Runable**

```java
public class ThreadTest02 {
    public static void main(String[] args) {
        Thread t= new Thread(new MyThread02());
        t.start();
        for(int i=0;i<1000;i++)
            System.out.println("主线程"+i);
    }
}


class MyThread02 implements Runnable{
    @Override
    public void run() {
        //编写程序，这段程序的代码属于子线程，在子栈中运行
        for(int i=0;i<1000;i++)
            System.out.println("分支线程"+i);
    }

}
```

**方式三：（JDK8新特性）实现Callable接口**

这种方式实现的线程可以获取线程的返回值。

```java
public class ThreadTest07 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("call method begin");
                Thread.sleep(1000*10);
                System.out.println("call method end!");

                int a =100;
                int b =200;
                return a+b;
            }
        });
        //创建线程对象
        Thread  t = new Thread(task);
        //这里是main方法，这是在主线程中
        t.start();
        //获取返回对象结果，但是当前线程会阻塞
        Object obj = task.get();
        System.out.println(obj);
    }
}
```

==此方法会造成线程阻塞，效率很低。==

最好是利用方式二，因为接口可以无限实现，但是只能继承一个。

**获取线程名字**

```java
public class ThreadTest03 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread03());
        Thread t2 = new Thread(new MyThread03());
        //设置线程名字
        t1.setName("t1");
        t1.start();

        t2.setName("t2");
        t2.start();
    }
}

class MyThread03 implements Runnable{

    @Override
    public void run() {
        try {
            for (int i=0;i<100;i++) {
                Thread.sleep(10);

                //取得当前线程，并获取名字
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName()+":"+i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
```

**关于sleep的面试题**

如上代码，加上：

```java
public class ThreadTest03 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread03());
        Thread t2 = new Thread(new MyThread03());
        //设置线程名字
        t1.setName("t1");
        t1.start();

        try{
            t1.sleep(10);  //并不会t1线程进行休眠
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        t2.setName("t2");
        t2.start();
    }
}
```

sleep是一个静态方法，执行时会将t1.sleep()转换为Thread.sleep()，休眠的是当前线程。

**中断sleep**

方式一：利用异常中断睡眠

```java
public class ThreadTest04 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Mythread04());
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }

}

class Mythread04 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"---> begin");
        try {
            Thread.sleep(1000*60);
        } catch (InterruptedException e) {
            e.printStackTrace();  //异常是在这里抛出的
        }
        System.out.println(Thread.currentThread().getName()+"---> end");
    }
}
```

方式二：

stop方法，==已经弃用。直接杀死线程，容易丢失数据。==

方式三：利用flag标记，轮询的方式（我觉得这种方式，也很傻比）

## 线程的生命周期

![image-20210103190357944](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20210103190357944.png)

关于线程对象的生命状态：

- 新建状态
- 就绪状态
- 运行状态
- 阻塞状态
- 死亡状态

## 线程的调度（了解）

1. 抢占式调度模型（java）

2. 均分式调度模型（时间片轮转法）

   > 1.抢占式线程调度，每个线程可能会有自己的优先级，但是优先及并不意味着高优先级的线程一定会被调度，而是由cup随机的选择，所谓抢占式的线程调度，就是说一个线程在执行自己的任务时，虽然任务还没有执行完，但是cpu会迫使它暂停，让其它线程占有cpu的使用权。
   >
   > 2.协作式线程调度，每个线程可以有自己的优先级，但优先级并不意味着高优先级的线程一定会被最先调度，而是由cpu时机选择的，所谓协作式的线程调度，就是说一个线程在执行自己的任务时，不允许被中途打断，一定等当前线程将任务执行完毕后才会释放对cpu的占有，其它线程才可以抢占该cpu。

==在java1.2之后，所有的Java线程会被1:1地映射到OS上面，线程的调度完全取决于操作系统.==

线程调度的方法：

实例方法：

```java
void setPriority(int newPriority)//设置线程的优先级
```

最低优先级1,....默认5，....最高优先级10(）。==优先级高只意味着时间片多一些，而不是两个优先级不同的线程，优先级高的先获得执行权。==

```java
void join()//抢占当前线程
```

```java
public class ThreadTest05 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MyThread05());
        t.start();
        System.out.println("main..start...");
        t.join();
        System.out.println("main...end...");//需要run()执行完才会执行end

    }
}


class MyThread05 implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<100;i++)
            System.out.println(i);
    }
}
```

静态方法：

```java
static void yield() //让位，不是阻塞
```

## 线程安全（至关重要）

1. 数据的不安全问题：

   - 条件1：多线程并发
   - 条件2：有共享数据
   - 条件3：共享数据有修改的行为

   同时满足就可能出现线程安全问题。==因此，局部变量不可能出现线程安全问题，静态变量（方法区中）和实例变量（堆中）会存在线程安全问题。==

2. 怎么解决线程安全问题？

   当多线成并发的环境下，有共享数据，并且这个数据还会被修改，此时就存在线程安全问题，怎么解决这个问题？

   线程排队执行。（不能并发）

   用排队执行解决线程安全问题。

   这种机制被称为：**线程同步机制。**

3. 线程同步代码块

   语法：synchronized(){  } '()'内放线程共享的对象。

   ```java
   public class ThreadSafeTest01 {
       public static void main(String[] args) {
           Account account = new Account(10000);
           Thread t1 = new Thread(new MyThread06(account));
           Thread t2 = new Thread(new MyThread06(account));
   
           t1.start();
           t2.start();
       }
   }
   
   class MyThread06 implements Runnable{
       final private Account account;
   
       public MyThread06(Account account) {
           this.account = account;
       }
   
       @Override
       public void run() {
   
           System.out.println(account.withDrawal(5000));
       }
   }
   
   class Account{
       private int balance;
   
       public Account(int balance) {
           this.balance = balance;
       }
   
       public int getBalance() {
           return balance;
       }
   
       public int withDrawal(int money)  {
   
           synchronized (this) {
               //在这里获取当前余额
               int before = getBalance();
               try {
                   //睡眠10ms模拟网络延迟
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               balance = before - money;
               return balance;
           }
       }
   }
   ```

   假设t1和t2线程并发，加入t1先执行，遇到synchronized 会自动找后面共享对象的对象锁，找到之后，并占有这把锁，执行完所有代码块才会归还。当t1还在执行的时候，t2也开始执行，碰到对象锁就会等待，直到对象锁被t1归还。

   每次遇到synchronized关键字就会放弃时间去锁池中寻找对象锁，如果对象锁没找到，就一直等待，如果找到了就继续抢夺时间片。

   ==千万注意：对象锁并不一定是我们要守护的数据，只要是两个线程共享的对象都可以拿来做对象锁。当前于一个标志信号。==

   ```java
   public class ThreadSafeTest01 {
       public static void main(String[] args) {
   
           Object obj = new Object();//用一个obj来做共享对象
           Account account = new Account(10000,obj);
   
           Thread t1 = new Thread(new MyThread06(account,obj));
           Thread t2 = new Thread(new MyThread06(account,obj));
   
           t1.start();
           t2.start();
       }
   }
   
   class MyThread06 implements Runnable{
       final private Account account;
       final private Object obj;
   
       public MyThread06(Account account, Object obj) {
           this.account = account;
           this.obj = obj;
       }
   
       @Override
       public void run() {
   
           System.out.println(account.withDrawal(5000,obj));
       }
   }
   
   class Account{
       private int balance;
   
       public Account(int balance, Object object) {
           this.balance = balance;
       }
   
       public int getBalance() {
           return balance;
       }
   
       public int withDrawal(int money,Object obj)  {
   
           synchronized (obj) {
               //在这里获取当前余额
               int before = getBalance();
               try {
                   //睡眠10ms模拟网络延迟
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               balance = before - money;
               return balance;
           }
       }
   }
   ```

   这里synchronized传入的跟Account并不是一个对象，也与balance一点关系都没有，但是跟t1与t2共享，所以t1在执行时，obj的对象锁被取走，t2执行不了，balance就安全了。

   **synchronized可以出现在实例方法上，但是一定锁的是this对象，不灵活。。。。。**

   **synchronized可以出现在静态方法上，但是一定锁的是类，类锁只有一把。**

4. 死锁现象

   两个线程分别拥有相同的两个对象锁（A,B），一个先锁A，一个先锁B。

   ![image-20210104145701489](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20210104145701489.png)

   死锁示例：

   ```java
   public class DeadLockTest {
       public static void main(String[] args) {
           Object o1 = new Object();
           Object o2 = new Object();
   
           Thread t1 = new Thread(new MyThread07(o1,o2));
           Thread t2 = new Thread(new MyThread07(o2,o1));
   
           t1.setName("t1");
           t2.setName("t2");
   
           t1.start();
           t2.start();
   
       }
   }
   
   
   class MyThread07 implements Runnable{
       private Object o1;
       private Object o2;
   
       public MyThread07(Object o1, Object o2) {
           this.o1 = o1;
           this.o2 = o2;
       }
   
       @Override
       public void run() {
           synchronized (o1){
               count();
               synchronized (o2){
                   count();
               }
           }
   
   
       }
   
       private void count(){
           Thread current = Thread.currentThread();
           for(int i =0;i<1000;i++){
               try {
                   Thread.sleep(1);
                   System.out.println(current.getName()+"----->:"+i);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
       }
   }
   ```

5. 解决线程安全问题

   synchronized会让程序的执行效率降低，用户体验不好。系统的用户吞吐量降低。用户体验插。在不得已的情况下再选择线程同步机制。

   第一种方案：尽量使用局部变量代替“实例变量和静态变量”

   第二种方案：如果必须是实例变量，那么可以考虑创建多个对象，这样的实例变量的内存就不共享了。（一个线程对应1个对象，100个线程对应100个对象，对象不共享，就没有数据安全的问题了）。

   第三种方案：如果不能使用局部变量，对象也不能创建多个，这个时候就只能选择synchronized了。

## 守护线程

java语言中分为两大类：

- 用户线程
- 守护线程（最具代表的就是垃圾回收机制）

守护线程的特点：

一般守护线程是一个死循环，所有的用户线程只要结束，守护线程自动结束。

注意：主线程main方法是一个用户线程。

守护线程用在什么地方？

每天定时备份，定时器。

```java
public class ThreadTest06 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MyThread08());
        t.setName("t");
        t.setDaemon(true); //设置为守护线程
        t.start();
        for (int i=0;i<10;i++){
            Thread.sleep(1000);
        }

    }
}


class MyThread08 implements Runnable{
    @Override
    public void run() {
        int i=0;
        while (true){
            System.out.println(Thread.currentThread().getName()+"----->"+(i++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
```

## 定时器

1. 方式一：sleep()

2. 方式二：java.util.Timer

   - schedule(TimerTask task,Date firstTime,[long period]);
   - cancel()

   TimerTask是一个抽象方法，需要继承重写run(),可以用匿名内部类

   ```java
   public class TimerTest {
       public static void main(String[] args) throws ParseException {
           Timer timer = new Timer();
           SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
           Date firstTime = sdf.parse("19:23:00");
           //每2s执行一次
           timer.schedule(new TimerTask() {
               @Override
               public void run() {
                   Date currentTime = new Date();
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   System.out.println(sdf.format(currentTime));
               }
           },firstTime, 1000 * 2);
       }
   
       public static void printTask(){
           System.out.println("time is ok...");
       }
   }
   ```

## wait和notify方法

**这两个方法不是线程对象的方法，是java中任何一个java对象的方法，因为这两个方式是Object类中自带的。wait()方法和notify()方法不是通过线程对象调用。**

wait()方法作用？表示：让正在o对象上活动的线程进入等待状态，并且释放掉t线程之前占有的o对象的锁，无限期等待，直到被唤醒为止。

notify()方法作用？表示：唤醒正在o对象上等待的线程，只是通知，不会释放掉o对象上之前占有的锁。

还有一个notifyAll()方法：这个方法是唤醒o对象上处于等待的所有线程。

## 生产者和消费者模式

一个线程负责生产，一个线程负责消费，最终达到生产消费均衡。

![image-20210104204852589](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20210104204852589.png)

利用wait和必要的逻辑判断，不管先执行哪个，都是保证要生产者生产，并且消费者消费后再次生产的执行顺序。

```java
public class ThreadTest08 {
    public static void main(String[] args) {
        List list = new ArrayList();


        Thread t1 = new Thread(new Producer(list));
        Thread t2 = new Thread(new Consumer(list));

        t1.setName("Producer");
        t2.setName("Consumer");
        t1.start();
        t2.start();
    }
}

//生产者线程
class Producer implements Runnable{
    private List list;

    public Producer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        while(true) {
            //当前线程拿到对象锁
            synchronized (list) {
                if (list.size() > 0) {
                    try {
                        list.wait();//释放对象锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //程序能够执行到这里，说明仓库是空的，可以生产
                Object obj = new Object();
                list.add(obj);
                System.out.println(Thread.currentThread().getName() + "---->" + obj);
                //唤醒消费者进行消费
                list.notifyAll();
            }
        }
    }
}


//消费者线程
class Consumer implements Runnable{
    private List list;

    public Consumer(List list) {
        this.list = list;
    }

    @Override
    public void run() {

        while(true) {
            synchronized (list) {
                if (list.size() == 0) {
                    try {
                        //仓库已经空了
                        //消费者线程等待，释放掉list集合的锁
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //程序能够执行到此处说明仓库中有数据，进行消费
                Object obj = list.remove(0);
                System.out.println(Thread.currentThread().getName() + "------>" + obj);
                //唤醒生产者生产
                list.notifyAll();
            }
        }
    }
}
```



# 反射机制

to be continue...

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

