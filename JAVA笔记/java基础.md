[toc](java学习笔记)

# java前言

## java岗位

- java后端
- java客户端

## java IDE

- idea
- eclipse

# java编写规范

## 命名规范

- 见名知意
- 驼峰命名
- 类名、接口名首字母大写，后面首字母大写
- 变量名、方法名首字母小写，后面首字母大写
- 常量名，全部大写，并且单词与单词之间采用下划线衔接（例如：PORT_NUM）

## 注释规范

## 文件规范



# java特性

- 可移植性

主要靠jvm，不同平台的jvm是不一样的，但留给开发者的接口是一样的。

![image-20200911220747319](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200911220747319.png)

- JDK、JRE、JVM三者之间的关系

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200911221422751.png" alt="image-20200911221422751" style="zoom:50%;" />

- java的加载与执行

java->编译->.class 字节码(字节码也是可移植的，一次编译多地运行)

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200911224324806.png" alt="image-20200911224324806" style="zoom: 67%;" />

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200911222827188.png" alt="image-20200911222827188" style="zoom:67%;" />  

![image-20200914211105579](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200914211105579.png)

执行顺序：启动JVM->启动classloader->找到字节码->

![image-20200914212148476](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200914212148476.png)

# java基础语法规定

- 一个java文件只能有一个public类，必须于文件同名。
- java是大小写严格区分的，但是不能利用大小写区分两个类名，否则只有一个能被编译。
- java的变量声明后必须赋值。
- java中有一个原则就是就近原则，全局变量和局部变量同名，优先使用局部变量。 

# 数据类型

## 基本数据类型

- java中的char占2字符，全球文字采用unicode编码。

- byte  -128~127

- short   -32768-32577

- boolean

- ........

- 整数的表示方式

  | 类型     | 表示方式（2） |
  | -------- | ------------- |
  | 十进制   | 2             |
  | 8进制    | 020           |
  | 十六进制 | 0x02          |
  | 二进制   | 0b10          |

- 在java中，整数型字面量，java会当成int来处理，比如`long  a= 2147483648；`会报错。正确做法`long  a= 2147483648L；`

- java在进行精度舍去的时候 ，保留的是补码低位的值，高位被舍去，。`short a=(short)65537;`得到的a的值为1

- 混合运算时，2个字节的数据类型都会被转成int。

- float  4个字节，**任意一个float的储存容量都大于整数型，因为是以次幂储存的**

- double 8个字节

- 浮点数的表示方式

- | 类型   | 表示方式 | 备注                          |
  | ------ | -------- | ----------------------------- |
  | float  | 3.14F    | 默认都是以double储存，除非加F |
  | double | 3.14     |                               |

  

## 引用数据类型

- String
- 以及其余对象类
- java中所有的类都是引用类型

## 运算符

- 字符串连接运算符 + 可实现数字+字符串

  

# 控制语句

- switch

  JDK8之前只支持int，之后版本支持String类型。byte，short，char会转成int数字。

# 控制台输入输出

- 输入

  ```
  java.
  ```


# JVM内存结构

JVM下主要存在3个区：栈内存、堆区、方法区。但其实还有其他区。

- 方法区

  用于储存字节码，也就是储存代码片段

- 栈（stack）

  - 在方法被调用的时候，该方法需要的内存空间在栈中分配。只有在调用的时候才会在栈中分配空间，并且调用时就是压栈。方法只有执行结束的之后，该方法所需要的空间就会释 放，此时发生弹栈动作。

  - 特别注意的是：如果方法再调用了其余方法，那么会在此方法的栈空间上面（不是在内部，是新的压栈）在添加一个方法栈，直到被调用的方法执行完毕出栈后，才会再次操作原方法的栈空间，接着执行。==不是以前以为是直接在原方法的栈空间内部还开了空间，这是不对的==。
  - 递归可能导致堆栈溢出

- 堆区

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200922233922917.png" alt="image-20200922233922917" style="zoom: 50%;" />

 # 面向对象编程

## java中的对象

java是一门完全是一门面向对象的编程语言。

- 什么是面向过程？

  面向过程的开发方式的主要特点是：注重步骤，注重的是实现功能的步骤。注重因果关系。代码与代码之间的耦合度太高，扩展力太差。

- 什么是面向对象？ 

  采用面向对象的方式进行开发，更符合人类的思维方式。（面向对象成为主流的原因）

- 类=属性(名词)+方法(动词)

## 类的定义

- 成员变量+局部变量

- 成员变量没有手动赋值的时候，系统默认赋值。

  | 类型         | 默认值 |
  | ------------ | :----- |
  | byte         | 0      |
  | short        | 0      |
  | int          | 0      |
  | long         | 0L     |
  | float        | 0.0F   |
  | double       | 0.0    |
  | boolean      | false  |
  | char         | \u0000 |
  | 引用数据类型 | null   |

- 成员变量经过类的实例化后，变成实例变量，不能通过类名访问实例变量。

- 成员变量分为实例变量和静态变量

- 静态

  - 静态变量：

    - 类所具备的特征，跟实例无关，不会随着对象的改变而改变；
    
    - ==静态变量在类加载时初始化，不需要new对象，静态变量的空间就开出来了，静态变量储存在**方法区**。==
    
    - 静态变量的内存图。
    
  
- 实例
  - 实例变量：
  - 会占用两份内存
  - ![image-20200927131332390](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200927131332390.png)

- this指针

  就是实例对象本身，保存在实例对象中。this通常可以省略，除了以下的情况：成员变量和形参或者局部参数同名，java具有就近原则。


## 类的JVM内存

- 凡是通过new运算符创建的对象，都储存在堆内存当中，new运算符的作用就是在堆内存中开辟一块内存空间。

- 无论是局部变量的类变量还是全局变量的类变量，其实例对象都保存在堆中，变量保存的是其在堆内的地址。于是称对象为引用类型。

- 对象和引用的区别？ 

  对象是通过new出来的，在堆内存中储存。引用是：但凡是变量，并且该变量中保存了内存地址指向了堆内存当中的对象的。

-  对象与引用的内存分配图：![image-20200924213029973](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200924213029973.png)

- 中文是可以做类名和变量名的。 

- 关于类的嵌套

  <img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200924223811784.png" alt="image-20200924223811784" style="zoom:50%;" />

  - 第一步，实例化u1对象

  <img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200924222757133.png" alt="image-20200924222757133" style="zoom:50%;" />

  - 对对象赋值

     <img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200924230532660.png" alt="image-20200924230532660" style="zoom:50%;" />

## 类的使用

- 成员变量中的实例变量，必须通过引用变量来访问，不能以类.

```java
class T{
	A a;	
	public static void main(String[] args){
		D d=new D();
		C c=new C();
		B b=new B();
		A a=new A();
		T t=new T();  //T已经实例化了
		
		c.d =d;
		b.c =c;
		a.b =b;
		t.a =a;
		
		//利用t访问i，哪怕是在本类，也只能通过实例变量来访问
		System.out.println(t.a.b.c.d.i);
	}
}
class A{
	B b;
}
class B{
	C c;
}
class C{
	D d;
}
class D{
	int i;
}
```

- 空指针异常

  

![image-20200924233427464](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20200924233427464.png)

注：==垃圾回收机制主要回收的是堆的数据。==

# 方法

## 静态方法

- 只能由类名访问
- 静态方法只能操作静态成员（静态变量）
- ==静态方法也会在栈里创建，只要是方法执行，都会产生压栈==
- 工具类中的方法一般都是static的

## 实例方法

-  实例方法可以操作实例变量，实例方法已经静态变量，静态方法。

## 构造方法

- 当一个类没有提供构造函数时，会默认提供不带参数的构造函数
- 成员变量在构造函数中得到默认值赋值或者根据参数赋值
- 构造方法两个作用：创建对象；给属性赋值
- 方法的函数签名，只能由方法名和入口参数确定，不能由形式参数名字区别
- 只能用new来调用。

## 方法的参数传递

[深入理解Java中的方法的参数传递机制](https://www.cnblogs.com/sum-41/p/10799555.html)

在java中，不存在真正的引用传递的情况，对于引用数据类型，其真实的储存空间都是在堆上，变量名都只保存了堆上的地址，在实参传递入方法的时候，实际上创建了一个引用的拷贝，对此引用的对象操作会造成对象的改变，但是改变引用指向的对象，并不会对实参造成任何影响。

分析下列代码：

```java
public class HelloWorld {
    public static void main(String[] args){
        A a=new A(0);
        System.out.println("before change a:"+a.value+" addr:"+System.identityHashCode(a));
        changeValue(a);
        System.out.println("after change value a:"+a.value+" addr:"+System.identityHashCode(a));
        changeObj(a);
        System.out.println("after change obj a:"+a.value+" addr:"+System.identityHashCode(a));
    }
    //只是改变对方的值
    static void changeValue(A a){
        a.value=10;
        System.out.println("addr:"+System.identityHashCode(a));
    }

    static void changeObj(A a){
        A b=new A(20);
        a=b;
        System.out.println("addr:"+System.identityHashCode(a));
    }


}

class A{
    public int value;
    public A(int value){
        this.value=value;
    }
}
```

输出：

```bash
before change a:0 addr:189568618
addr:189568618
after change value a:10 addr:189568618
addr:186370029
after change obj a:10 addr:189568618

Process finished with exit code 0

```

可以看到，对于实参a，引用的地址永远没有发生改变，只有重新创了个对象，赋值给内部变量a时，a的地址发生了改变，这时与实参a指向了不同的地址。

内存图：

![image-20201011115315436](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201011115315436.png)

从图中可以看出，实参中的a对对象A的引用从未改变，方法内产生了对引用的拷贝，可改变指向哪一个引用对象。

# 静态/实例代码块

##  静态代码块

```java
public class StaticCode{
	static{
		System.out.println("execute to here");
	}		
	public static void main(String[] args){
		;
	}
}
```

  ==静态代码块在类加载的时候执行，并且在main方法之前执行，一般按照自上而下的顺序执行，只执行一次。==

静态代码块的作用：可用于记录类加载的日志，记录在类加载的时间等。

## 实例代码块

```java
public class StaticCode{
	static int i=100;
	static{
		System.out.println(i+"execute to static code");//可以访问实例变量
	}	
	
	{
		System.out.println("execute to incetance code");//
	}
	
	public StaticCode(){
		System.out.println("execute to Construct code");//
	}
	public static void main(String[] args){
		StaticCode s1=new StaticCode();
		StaticCode s2=new StaticCode();
		
	}
}
```

==实例代码块在实例化的时候执行，实例化多少次，就会执行多少次。并且执行在构造方法之前。==



# 封装、重载、多态、继承

## 封装

- public、private、protect

- 属性私有化

- get、set是实例方法（只要不带static的都是实例方法） 

  主要针对非public的属性，进行设置与访问

  java开发规范有要求，set方法和get方法要满足以下格式：

  - public 返回值类型 get+属性名首字母大写(无参)
  - public void set+属性名首字母大写(一个参数)

## 继承

继承关键字：**extends**

继承的相关特性：

- java中的继承支持单继承，不支持多继承，c++支持多继承，这也是java体现简单性的一点。

- 虽然java不支持多继承，但是有间接继承。C继承B,B继承A。

- Java中规定，子类继承父类，除构造方法不能继承之外，其余都可以继承。只是private属性不能在子类中直接访问。

- **子类实例化会造成父类被实例化，父类的构造方法会被先执行。同时，对于父类的带参构造方法，继承的时候必须手动调用父类构造方法**

  比如：

  ```java
  class A{
  //    public A(){
  //        System.out.println("A is called");
  //    }
      public A(int i){
          System.out.println("A is called");
      }
  }
  
  class B extends A{
      public B(){
          System.out.println("B is called");
      }
  }
  ```

  父类只有一个带参构造函数，所以必须要显式的调用父类的构造方法。

  继承举例：

```java
public class Inherit{
	public static void main(String[] args){
		CreditAccount c = new CreditAccount(100);
		c.printInfo();
	}
	
}

/**
*/

class Account{
	private String accID;
	private String name;
	
	public Account(String accID,String name){
		setAccID(accID);
		setName(name);
	}
	public Account(){
		
	}
	
	public void setAccID(String accID){
		this.accID=accID;
	}
	
	public String getAccID(){
		return this.accID;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
}
/**

*/
class CreditAccount extends Account{//继承于Account
	private int creditScorce;
	
	public CreditAccount(int creditScorce){
		setCreditScore(creditScorce);
	}
	public CreditAccount(){
		
	}
	
	public void setCreditScore(int creditScorce){
		this.creditScorce=creditScorce;
	}
	public int getCreditScorce(){
		return this.creditScorce;
	}
	
	public void printInfo(){
		System.out.println("accID:"+getAccID()+";\ncreditScorce:"+getCreditScorce());
	}
	 
}
```

## 重载

同一个类，函数签名不同构成重载，函数签名不包括返回类型。（函数名是大小写敏感的）

## 覆盖

子类继承父类后，有一些行为必须要进行针对性改进。直接将继承过来的方法重新在子类中重新写一遍就可以了。

条件：

- 子类覆写的方法的访问权限不能更低。

- 同时抛出的异常不能更多。

- 构造方法不能够被覆盖

**object类**

- toString()方法，（println对象，会自动调用toString()方法）

  ```java
  public class HelloWorld {
      public static void main(String[] args) {
          Object obj=new Object();
          System.out.println(obj.toString());
          System.out.println(obj);
      }
  }
  ```

  ```bash
  java.lang.Object@10f87f48
  java.lang.Object@10f87f48
  ```

  其中10f87f48是对象堆地址的hash码。
  
  通常我们需要进行覆盖。

```java
class Person extends Object{
    private String name;
    public Person(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

## 多态

多态：多种型态

1. 向上转型

```java
public class Polymorphism {
    public static void main(String[] args) {
        Animal a =new Bird();//这就是向上转型，就是子转向父
        a.move();
    }
}

class Animal{
    public void move(){
        System.out.println("animal is moving");
    }

}
class Bird extends Animal{
    @Override
    public void move() {
        System.out.println("Bird is flying!");//方法覆盖
    }
}
```

java程序分为编译阶段和运行阶段。

1. 先来分析编译阶段：对于编译器来说，编译器只知道a是Animal类型，所以编译器在检查语法的时候，会到Animal.class文件中找Animal.move()方法，找到了之后会绑定move()方法。编译通过。静态绑定成功。（==编译阶段绑定==）

2. 再来分析运行阶段：运行阶段的时候，实际上在堆内存中创建的java对象是Bird对象，所以move的时候，真正参与move的对象是一只猫，所以运行阶段会动态执行Cat对象的move()方法。这个过程属于运行阶段绑定。（==运行阶段绑定属于动态绑定==）
  3. 多态指的是编译阶段和运行阶段具有不同的形态。
  4. ==综上所述，要成功实现多态，必须要完成静态绑定和动态绑定，在向上转型的时候，调用的方法，必须要是父类和子类都有的**属性和方法**。==

  示例：

  ```java
  public class Polymorphism {
      public static void main(String[] args) {
          Animal a =new Bird();
          Bird b=new Bird();
          a.move();
          System.out.println(b.category);
          System.out.println(a.category);
      }
  }
  
  
  class Animal{
      public void move(){
          System.out.println("animal is moving");
      }
  
  }
  
  
  class Bird extends Animal{
      public String category="Bird";
      @Override
      public void move() {
          System.out.println("Bird is flying!");//方法覆盖
      }
  }
  ```

  此代码不能通过编译。因为编译的时候Animal对象a找不到category属性。

2. 向下转型（**存在风险**）

```java
public class Polymorphism {
    public static void main(String[] args) {

/*向上转型
        Animal a =new Bird();
        Bird b=new Bird();
        a.move();
        System.out.println(b.category);
        System.out.println(a.category);
*/
        Animal a=new Animal();
        Bird b=(Bird)a;//这里就是向下转换
        System.out.println(b.category);

    }
}
```

正如上面所述，多态必须要通过静态绑定和动态绑定，上述代码，编译不会报错，因为Bird对象是存在category的，但是实际对象是一个Animal对象，没有category属性，在运行时会报错：

```bash
Exception in thread "main" java.lang.ClassCastException: class Animal cannot be cast to class Bird (Animal and Bird are in unnamed module of loader 'app')
	at Polymorphism.main(Polymorphism.java:12
```

- **instanceof运算符**

  ```java
          Animal a=new Animal();
          if(a instanceof Bird){
              Bird b=(Bird)a;
              System.out.println(b.category);
          }
  ```

  注意：instanceof不能在强制转换过后进行判断，强制转换后，instanceof只能判断为强制转换后的类型。

  同时，instanceof在实际运行的时候，向上转型能够被识别位正确的obj类型。

  ```java
          Animal a =new Bird();
          if(a instanceof Bird){
              Bird b=(Bird)a;
              System.out.println(b.category);
          }
  ```

- **多态的作用**
  - 为了在软件和开发和维护中遵守OCP原则，降低程序的耦合度，提高程序的扩展力；
  - 代码举例：

```java
public class Polymorphism {
    public static void main(String[] args) {
        Observer observer=new Observer();
        Cat cat=new Cat();
        Bird bird =new Bird();
        observer.decribe(cat); //利用多态机制可以保证类似功能的扩展
        observer.decribe(bird);


    }
}
class Animal{
    public void move(){}

}

class Cat extends Animal{
    public String category="Cat";
    public void move(){
        System.out.println("Cat is crawling!");
    }
}

class Bird extends Animal{
    public String category="Bird";
    @Override
    public void move(){
        System.out.println("Bird is flying!");//方法覆盖
    }
}


class Observer{
    public void decribe(Animal animal){
        animal.move();
    }
}
```

在Observer.decribe()方法中，参数带入父类，利用多态机制就可以传入不同的对象，完成功能的扩展。

面向Animal类编程，Observer类对Bird和Cat的耦合度就降低了。

- 静态方法不存在覆盖

  ```java
  public class Polymorphism {
      public static void main(String[] args) {
          
          Animal a =new Bird();
          Animal b =new Cat();
          Observer observer=new Observer();
          observer.observe(a);
          observer.observe(a);
  
      }
  }
  
  class Animal{
      public void move(){
          System.out.println("animal is moving");
      }
  
      public static void eat(){
          System.out.println("animal is eating!");
      }
  
  }
  
  
  class Bird extends Animal{
      public String category="Bird";
  
      public void move() {
          System.out.println("Bird is flying!");//方法覆盖
      }
      public static void eat(){
          System.out.println("Bird is eating!");
      }
  }
  class Cat extends Animal{
      public String category="Cat";
  
      public void move() {
          System.out.println("Cat is flying!");//方法覆盖
      }
      public static void eat(){
          System.out.println("Cat is eating!");
      }
  }
  
  class Observer{
      public void observe(Animal animal){
          animal.move();
          animal.eat();
      }
  }
  ```

  输出：

  ```bash
  Bird is flying!
  animal is eating!
  Bird is flying!
  animal is eating!
  ```

  静态方法跟实例对象无关，所以还是执行的Animal.ear()。

- 私有方法无法覆盖

## 补充 

**super指针** 

- super能出现在实例方法和构造方法中。

- super的语法是"super."和"super（）"

- super不能使用在静态方法中；

- super只能出现在构造方法的第一行，通过当前的构造方法去调用"父类"中的构造方法，目的是；创建子类对象的时候，先初始化父类型特征。

- 父类只有带参构造方法时，必须要用super显式地调用父类的构造方法。

- super调用背后的原因：

  <img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20201016012202172.png" alt="image-20201016012202172" style="zoom:50%;" />

- super可越过子类，调用父类的属性与方法。

  ```java
  class A{
  //    public A(){
  //        System.out.println("A is called");
  //    }
      public A(int i){
          System.out.println("A is called");
      }
      public void test(){
          System.out.println("A is tested");
      }
  }
  
  class B extends A{
      public B(){
          super(9);
          System.out.println("B is called");
          super.test();
      }
      public void test(){
          System.out.println("B is tested");
      }
  }
  ```

  ```bash
  A is called
  B is called
  A is test
  ed
  ```

- super指针和this指针不同，不是对自身对象的引用。super只是代表父类的那一部分特征。它引用当前对象的直接父类中的成员（用来访问直接父类中被隐藏的父类中成员数据或函数，基类与派生类中有相同成员定义时如：super.变量名  super.成员函数据名（实参）

  

##  