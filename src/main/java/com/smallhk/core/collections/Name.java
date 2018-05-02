package com.smallhk.core.collections;

public class Name {
	private String firstName;
	private String lastName;
	
	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		if(o instanceof Name){
			Name name = (Name)o;
			return name.firstName.equals(firstName) && name.lastName.equals(lastName);
		}
		return false;
	}

	@Override
	public int hashCode(){
		return firstName.hashCode() >> lastName.hashCode();
	}
	
	@Override
	public String toString(){
		return "Name[" + this.firstName + "," + this.lastName + "]";
	}
}
