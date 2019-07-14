package org.dean.duck.core;

/**
 * @description this class will lead to  the recursion constructor calling 
 * @author eric
 * @since 2016年4月28日 上午9:56:59
 * @version 1.0
 *
 */
public class ConstructorRecursionTest {

	ConstructorRecursionTest c;
	{
		c = new ConstructorRecursionTest();
	}
	
	public ConstructorRecursionTest(){
		System.out.println("no parameter constructor");
	}

	public static void main(String[] args) {
		ConstructorRecursionTest ct = new ConstructorRecursionTest();
	}
}
