package com.smallhk.core.oop;

import com.smallhk.core.Employee;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Title. <br>
 * Description. instance creation
 * <p>
 * Copyright: Copyright (c) 2018/5/17
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class CreateInstanceDemo {
    public static void main(String[] args) throws Exception {
        method1();
        method2();
        method3();
        method4();
        method5();

    }



    /**
     * use the new keyword
     */
    private static void method1(){
        Employee employee = new Employee();
        System.out.println(employee.hashCode());
    }

    /**
     * call the method 'newInstance()' of Class
     */
    private static void method2(){
        School school = null;
        try {
            school = (School) Class.forName("com.smallhk.core.oop.School").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(school.hashCode());
    }

    /**
     * call the clone method,but the POJO have to implement the Cloneable interaface
     */
    private static void method3() {
        School school = new School();
        School school1 = (School) school.clone();
        System.out.println("school = " + school.hashCode() + ",school1 = " + school1.hashCode());
    }

    /**
     * call the method 'newInstance()' of Constructor
     */
    private static void method4() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<School> constructor = School.class.getConstructor();
        School school = constructor.newInstance();
        System.out.println(school.hashCode());

    }

    /**
     *  using Deserialization
     */
    private static void method5() throws IOException, ClassNotFoundException {
        School school = new School();
        school.setName("eric");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));
        out.writeObject(school);
        out.close();
        //Deserialization
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));
        School school1 = (School) in.readObject();
        in.close();
        System.out.println("school1.name = " + school1.getName());
        System.out.println(school + ", hashcode : " + school.hashCode());
        System.out.println(school1 + ", hashcode : " + school1.hashCode());
    }


}
