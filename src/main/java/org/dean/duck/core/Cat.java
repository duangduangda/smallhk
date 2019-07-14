package org.dean.duck.core;

public class Cat {
	private static int instanceCount = 0;
	
	public Cat(){
		System.out.println("no parameter constructor");
		instanceCount++;
	}
	
	public static long getInstanceCount(){
		return instanceCount;
	}
}
