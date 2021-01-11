package com.practice.reflect.property;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/*
 * 利用反射和配置文件，可以使:应用程序更新时，对源码无需进行任何修改
 * 只需要将新类发送给客户端，并修改配置文件即可
 */
public class Main {
    public static void main(String[] args) throws Exception {
        //1.通过反射获取Class对象
        Class clazz = Class.forName(getValue("className"));

        //2.获取show()方法
        Method m = clazz.getMethod(getValue("methodName"));

        //3.调用show()方法
        m.invoke(clazz.getConstructor().newInstance());
    }

    //此方法接收一个key，在配置文件中获取相应的value
    public static String getValue(String key) throws IOException {
        //获取配置文件的对象
        Properties pro = new Properties();
        //获取输入流
        String fileName = System.getProperty("user.dir") + File.separator + "src" + File.separator + "com" +
                File.separator + "practice" + File.separator + "reflect" + File.separator + "property" + File.separator + "pro.txt";
        FileReader in = new FileReader(fileName);
        //将流加载到配置文件对象中
        pro.load(in);
        in.close();
        //返回根据key获取的value值
        return pro.getProperty(key);
    }
}
