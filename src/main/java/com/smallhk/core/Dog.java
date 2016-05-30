package com.smallhk.core;

public class Dog implements Cloneable {

	private String name;
	private double weight;
	
	public Dog(String name, double weight) {
		System.out.println("two parameter constructor");
		this.name = name;
		this.weight = weight;
	}
	
	
	public Object clone(){
		Dog dog = null;
		try {
			dog = (Dog)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return dog;
	}
	
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		
		if(o instanceof Dog){
			Dog target = (Dog)o;
			return target.name.equals(this.name) && target.weight == this.weight;
		}
		
		return false;
	}
	
	public int hashCode(){
		return 	name.hashCode() * 17 + (int) weight;
	}
}
