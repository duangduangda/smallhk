package com.smallhk.core.jvm;

public class ConstantClass {

	public static final String HELLO = "hello,world";
	
	static{
		System.out.println("ConstantClass init..");
	}

}
