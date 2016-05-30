package com.smallhk.core.jvm;

/**
 * 
 * @description simple tests for class initialization
 * @author dongyh   
 * @since 2016年3月28日 下午2:02:23
 * @version 1.0
 *
 */
public class NotInitialization{
	
	public static void main(String[] args) {
		testExample3();
	}
	
	/**
	 * 对于静态字段，只有直接定义了这个字段的类才会被初始化，
	 * 因此如果子类来引用父类中定义的静态变量字段，只会触发父类的初始化而不会触发子类的初始化 。
	 * 至于是否要触发子类的加载和验证，由于虚拟机规范中没有明确规定，这点取决于虚拟机的具体实现
	 * 
	 */
	public static void testExample1(){
		System.out.println(SubClass.value);
	}
	
	/**
	 * 通过数组引用类，不会触发此类的初始化
	 */
	public static void testExample2(){
		SubClass []apps = new SubClass[6];
	}
	
	/**
	 * 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量类的初始化
	 */
	public static void testExample3(){
		System.out.println(ConstantClass.HELLO);
	}
}
