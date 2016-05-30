package com.smallhk.core.collections;

public class Name2 {
	private String firstName;
	private String lastName;
	
	public Name2(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		if(o instanceof Name2){
			Name2 name = (Name2)o;
			return name.firstName.equals(firstName);
		}
		return false;
	}
	
	public int hashCode(){
		return firstName.hashCode();
	}
	
	@Override
	public String toString(){
		return "Name[" + this.firstName + "," + this.lastName + "]";
	}
}
