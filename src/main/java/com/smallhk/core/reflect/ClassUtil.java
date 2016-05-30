package com.smallhk.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {

	public static void printClassMessage(Object obj){
		
		printClassDetailMessage(obj);
		
		printMethodMessage(obj);
		
		printFieldMessage(obj);
		
	}

	public static void printClassDetailMessage(Object obj) {
		//要获取类信息，首先要获取类类型
		Class c = obj.getClass();
		//获取类的名称
		System.out.println("类的名称：" + c.getName());
	}

	public static void printFieldMessage(Object obj) {
		Class c = obj.getClass();
		System.out.println("成员变量的信息：");
		Field[] fields = c.getDeclaredFields();
		for(Field field :fields){
			Class fieldType = field.getType();
			System.out.println(fieldType.getName() + " " + field.getName());
		}
	}

	/**
	 * Method类，方法对象
	 * 一个成员方法就是一个Method对象
	 * getMethods()方法获取的是所有的public函数，包括父类继承而来的
	 * getDeclaredMethods()获取的是所有该类的自己声明的方法
	 */
	public static void printMethodMessage(Object obj) {
		Class c = obj.getClass();
		Method []ms = c.getMethods();
		for(int i = 0;i < ms.length;i++){
			//获取方法返回值的类类型
			Class returnType = ms[i].getReturnType();
			System.out.print(returnType.getName() + " ");
			//获取方法的名称
			System.out.print(ms[i].getName() + "(");
			//获取参数类型
			Class[]paramType = ms[i].getParameterTypes();
			for(Class cl:paramType){
				System.out.print(cl.getName() + ",");
			}
			System.out.println(")");
		}
	}
}
