package com.smallhk.core;

public class OverloadAndOverride {

	public void info(String name,double count){
		System.out.println("name=" + name );
		System.out.println("count=" + count );
	}
	
	public void info(String name,int count){
		System.out.println("name=" + name );
		System.out.println("count=" + count );
	}

	public void send(Object obj,double count){
		System.out.println("obj=" + obj );
		System.out.println("count=" + count );
	}
	
	public void send(Object []objs,double count){
		System.out.println("objs=" + objs );
		System.out.println("count=" + count );
	}
}
