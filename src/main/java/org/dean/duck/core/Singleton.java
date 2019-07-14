package org.dean.duck.core;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Singleton implements Serializable{
	private static Singleton instance;
	private String name;
	
	private Singleton(String name) {
		System.out.println("one parameter constructor..");
		this.name = name;
	}
	
	public static Singleton getInstance(String name){
		if(null == instance){
			instance = new Singleton(name);
		}
		return instance;
	}
	
	public Object readResolve(){
		return instance;
	}
}
