package com.smallhk.core;

import java.io.Serializable;

public class Wolf implements Serializable {
	private String name;
	
	public Wolf(String name){
		this.name = name;
	}

	@Override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}
		
		if(o instanceof Wolf){
			Wolf w = (Wolf)o;
			return w.name.equals(this.name);
		}
		return false;
	}

	@Override
	public int hashCode(){
		return name.hashCode();
	}
}
