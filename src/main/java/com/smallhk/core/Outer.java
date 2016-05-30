package com.smallhk.core;

public class Outer {
	
	public void test() throws InstantiationException, IllegalAccessException{
		System.out.println(new Inner());
//		System.out.println(Inner.class.newInstance());
	}
	
	public class Inner{
		public String toString(){
			return "Inner Object";
		}
	}
}
