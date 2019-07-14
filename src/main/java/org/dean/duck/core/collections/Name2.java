package org.dean.duck.core.collections;

public class Name2 {
	private String firstName;
	private String lastName;
	
	public Name2(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
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
	@Override
	public int hashCode(){
		return firstName.hashCode();
	}
	
	@Override
	public String toString(){
		return "Name[" + this.firstName + "," + this.lastName + "]";
	}
}
