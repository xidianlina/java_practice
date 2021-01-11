package com.practice.reflect.constructor;

public class Student {
    //---------------6种构造方法-------------------
    //1.默认的构造方法
    Student(String str) {
        System.out.println("(默认)的构造方法 s = " + str);
    }

    //2.无参构造方法
    public Student() {
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    //3.有一个参数的构造方法
    public Student(char name) {
        System.out.println("姓名：" + name);
    }

    //4.有多个参数的构造方法
    public Student(String name, int age) {
        System.out.println("姓名：" + name + "年龄：" + age);//这的执行效率有问题，以后解决。
    }

    //5.受保护的构造方法
    protected Student(boolean n) {
        System.out.println("受保护的构造方法 n = " + n);
    }

    //6.私有构造方法
    private Student(int age) {
        System.out.println("私有的构造方法   年龄：" + age);
    }
}
