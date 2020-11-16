package cat.java8;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author nanyin
 * @class Reflect.java
 * @description Java反射
 * @create 20:18 2020-04-14
 */
public class Reflect {

    public void createClassObject(){
        try {
            Class<?> clazz = Class.forName("com.java8.IOTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class<? extends Reflect> clazz2 = Reflect.class;

        Reflect reflect = new Reflect();
        Class<? extends Reflect> clazz3 = reflect.getClass();
    }

    public void createClassField(){
        try {
            Class<?> clazz = Class.forName("com.java8.IOTest");
            // just public fields
            Field[] fields = clazz.getFields();
            Arrays.stream(fields).forEach(System.out::println);
            // 需要捕获 NoSuchFieldException
            clazz.getField("field");
            // public, protected, default (package) access, and private fields,
            clazz.getDeclaredFields();
            // 需要捕获 NoSuchFieldException
            clazz.getDeclaredField("field");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void createClassMethods(){
        try {
            Class<?> clazz = Class.forName("com.java8.IOTest");
            Method[] methods = clazz.getMethods();
            // NoSuchMethodException
            Method createClassField = clazz.getMethod("createClassField");

            clazz.getDeclaredMethods();
            clazz.getDeclaredMethod("createClassField");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void createClassConstructor(){
        try {
            Class<?> clazz = Class.forName("com.java8.IOTest");
            Constructor<?>[] constructors = clazz.getConstructors();
            // NoSuchMethodException
            clazz.getConstructor(Integer.class);
            clazz.getDeclaredConstructors();
            // 如果这个类是“其它类的构造函数中的内部类”，
            // 调用getEnclosingConstructor()就是这个类所在的构造函数
            clazz.getEnclosingConstructor();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void createClassInstance(){
        try {
            Class<?> clazz = Class.forName("com.java8.IOTest");
            Object o = clazz.newInstance();
            Constructor<?> constructor = clazz.getConstructor(Integer.class);
            constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException
                |InstantiationException
                |NoSuchMethodException
                |InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
