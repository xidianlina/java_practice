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
  ![class3](http://github.com/xidianlina/java_practice/raw/master/picture/class3.jpg)
  
三、反射的使用（这里使用Student类做演示）
------

# 1、获取Class对象的三种方式
## 1.1 Object ——> getClass();
## 1.2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
## 1.3 通过Class类的静态方法：forName（String  className）(常用)
> 其中1.1是因为Object类中的getClass方法、因为所有类都继承Object类。从而调用Object类来获取
  
![class4](http://github.com/xidianlina/java_practice/raw/master/picture/class4.jpg)

```Java
package com.practice;

import com.practice.constructor.Student;

public class Main {

    public static void main(String[] args) {
        //第一种方式获取Class对象
        //这一new 产生一个Student对象，一个Class对象。
        Student stu1 = new Student();
        //获取Class对象
        Class stuClass = stu1.getClass();
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = Student.class;
        //判断第一种方式获取的Class对象和第二种方式获取的是否是同一个
        System.out.println(stuClass == stuClass2);

        //第三种方式获取Class对象
        try {
            //注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            Class stuClass3 = Class.forName("com.practice.constructor.Student");
            //判断三种方式是否获取的是同一个Class对象
            System.out.println(stuClass3 == stuClass2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
```
* 注意：在运行期间，一个类，只有一个Class对象产生。
* 三种方式常用第三种，第一种对象都有了还要反射干什么。第二种需要导入类的包，依赖太强，不导包就抛编译错误。一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法。
```Java
package com.practice.constructor;

public class Student {
    //---------------构造方法-------------------
    //（默认的构造方法）
    Student(String str) {
        System.out.println("(默认)的构造方法 s = " + str);
    }

    //无参构造方法
    public Student() {
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    //有一个参数的构造方法
    public Student(char name) {
        System.out.println("姓名：" + name);
    }

    //有多个参数的构造方法
    public Student(String name, int age) {
        System.out.println("姓名：" + name + "年龄：" + age);//这的执行效率有问题，以后解决。
    }

    //受保护的构造方法
    protected Student(boolean n) {
        System.out.println("受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private Student(int age) {
        System.out.println("私有的构造方法   年龄：" + age);
    }
}
```

```Java
package com.practice.constructor;

import java.lang.reflect.Constructor;

/*
 * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
 *
 * 1.获取构造方法：
 * 		1).批量的方法：
 * 			public Constructor[] getConstructors()：所有"公有的"构造方法
            public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)

 * 		2).获取单个的方法，并调用：
 * 			public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
 * 			public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
 *
 * 			调用构造方法：
 * 			Constructor-->newInstance(Object... initargs)
 */
public class Constructors {
    public static void main(String[] args) throws Exception {
        //1.加载Class对象
        Class clazz = Class.forName("com.practice.constructor.Student");

        //2.获取所有公有构造方法
        System.out.println("\n**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }

        System.out.println("\n************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }

        System.out.println("\n*****************获取公有、无参的构造方法*******************************");
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。
        Constructor con = clazz.getConstructor(null);
        System.out.println("con = " + con);

        //调用构造方法
        //newInstance是 Constructor类的方法（管理构造函数的类）
        //newInstance(Object... initargs)使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。
        //它的返回值是T类型，所以newInstance是创建了一个构造方法的声明类的新实例对象。并为之调用
        Object obj = con.newInstance();
        System.out.println("obj = " + obj);
        Student stu = (Student) obj;

        System.out.println("\n******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(char.class);
        System.out.println("con2 = " + con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('男');
        System.out.println("obj = " + obj);
    }
}
```






















   
### 参考:
```
https://www.cnblogs.com/shiy/p/6526868.html
https://blog.csdn.net/lililuni/article/details/83449088
```