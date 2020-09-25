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

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200911220747319.png" alt="image-20200911220747319" style="zoom:50%;" />

- JDK、JRE、JVM三者之间的关系

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200911221422751.png" alt="image-20200911221422751" style="zoom:50%;" />

- java的加载与执行

java->编译->.class 字节码(字节码也是可移植的，一次编译多地运行)

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200911224324806.png" alt="image-20200911224324806" style="zoom:50%;" />

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200911222827188.png" alt="image-20200911222827188" style="zoom:50%;" />  

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200914211105579.png" alt="image-20200914211105579" style="zoom:50%;" />

执行顺序：启动JVM->启动classloader->找到字节码->

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200914212148476.png" alt="image-20200914212148476" style="zoom:50%;" />

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

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200922233922917.png" alt="image-20200922233922917" style="zoom:50%;" />

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
- 

  ## 对象的创建

-  

## 类的JVM内存

- 凡是通过new运算符创建的对象，都储存在堆内存当中，new运算符的作用就是在堆内存中开辟一块内存空间。

- 无论是局部变量的类变量还是全局变量的类变量，其实例对象都保存在堆中，变量保存的是其在堆内的地址。于是称对象为引用类型。

- 对象和引用的区别？

  对象是通过new出来的，在堆内存中储存。引用是：但凡是变量，并且该变量中保存了内存地址指向了堆内存当中的对象的。

-  对象与引用的内存分配图：![image-20200924213029973](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200924213029973.png)

- 中文是可以做类名和变量名的。 

- 关于类的嵌套

  <img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200924223811784.png" alt="image-20200924223811784" style="zoom:50%;" />

  - 第一步，实例化u1对象

  <img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200924222757133.png" alt="image-20200924222757133" style="zoom: 50%;" />

  - 对对象赋值

     <img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200924230532660.png" alt="image-20200924230532660" style="zoom:50%;" />

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

  

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200924233427464.png" alt="image-20200924233427464" style="zoom: 80%;" />

注：==垃圾回收机制主要回收的是堆的数据。==

# 方法的参数传递

总的来说，方法传递的都是变量存的值，==记住：无论是基本数据类型的变量，还是引用变量，都是复制了一份变量存的值，其中，对于基本数据类型的变量，存的是实际的基本数据类型的值；对于引用变量存的是地址。== 

 