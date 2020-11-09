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

  

## 具体作用



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

