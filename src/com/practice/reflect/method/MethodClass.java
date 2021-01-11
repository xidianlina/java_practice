package com.practice.reflect.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * 获取成员方法并调用：
 *
 * 1.批量的：
 * 		public Method[] getMethods():获取所有"公有方法"；（包含了父类的方法也包含Object类）
 * 		public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的)
 * 2.获取单个的：
 * 		public Method getMethod(String name,Class<?>... parameterTypes):
 * 					参数：
 * 						name : 方法名；
 * 						Class ... : 形参的Class类型对象
 * 		public Method getDeclaredMethod(String name,Class<?>... parameterTypes)
 *
 * 	 调用方法：
 * 		Method --> public Object invoke(Object obj,Object... args):
 * 					参数说明：
 * 					obj : 要调用方法的对象；
 * 					args:调用方式时所传递的实参；
):
 */
public class MethodClass {
    public static void main(String[] args) {
        //1.获取Class对象
        Class clazz = null;
        try {
            clazz = Class.forName("com.practice.reflect.method.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2.获取所有公有方法
        System.out.println("*******************获取所有的”公有“方法***********************");
        Method[] methodArray = clazz.getMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }

        //3.获取所有方法，包括私有方法
        System.out.println("\n***************获取所有的方法，包括私有的*******************");
        methodArray = clazz.getDeclaredMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }

        //4.获取公有的show1()方法
        System.out.println("***************获取公有的show1()方法*******************");
        Method m = null;
        try {
            //调用制定方法（所有包括私有的），需要传入两个参数，第一个是调用的方法名称，第二个是方法的形参类型，切记是类型。
            m = clazz.getMethod("show1", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(m);
        //实例化一个Student对象
        Object obj = null;
        try {
            obj = clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            m.invoke(obj, "刘德华");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //5.获取私有方法show4()
        try {
            m = clazz.getDeclaredMethod("show4", int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(m);
        //解除私有限定
        m.setAccessible(true);
        Object res = null;
        try {
            //需要两个参数，一个是要调用的对象（获取有反射），一个是实参
            res = m.invoke(obj, 20);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("返回值:" + res);
    }
}