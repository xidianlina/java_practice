Java 反射
======

>反射是框架设计的灵魂。使用的前提条件：必须先得到代表的字节码的Class，Class类用于表示.class文件（字节码）

一、反射的概述
------
* JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
* 要想解剖一个类,必须先要获取到该类的字节码文件对象。而解剖使用的就是Class类中的方法.所以先要获取到每一个字节码文件对应的Class类型的对象.
* 反射就是把java类中的各种成分映射成一个个的Java对象。例如：一个类有：成员变量、方法、构造方法、包等等信息，利用反射技术可以对一个类进行解剖，把个个组成部分映射成一个个对象。
   （其实：一个类中这些成员方法、构造方法、在加入类中都有一个类来描述）
   ![class](http://github.com/xidianlina/java_practice/raw/master/picture/class.jpg)
   如图是类的正常加载过程：反射的原理在与class对象。
   Class对象的由来是将class文件读入内存，并为之创建一个Class对象。
  
二、查看Class类在java中的api详解（1.7的API） 
------
 ![class2](http://github.com/xidianlina/java_practice/raw/master/picture/class2.jpg)
  
*  Class类的实例表示正在运行的 Java 应用程序中的类和接口。也就是jvm中有N多的实例每个类都有该Class对象。（包括基本数据类型）
*  Class没有公共构造方法。Class 对象是在加载类时由 Java 虚拟机以及通过调用类加载器中的defineClass 方法自动构造的。也就是这不需要我们自己去处理创建，JVM已经帮我们创建好了。
*  没有公共的构造方法，方法共有64个。
  
  
  
   
   
### 参考:
```
https://www.cnblogs.com/shiy/p/6526868.html
https://blog.csdn.net/lililuni/article/details/83449088
```