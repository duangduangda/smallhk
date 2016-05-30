package com.smallhk.core.dp.factory.abstractfactory;

public interface Tyre {

	public void revolve();
}

class LuxuryTyre implements Tyre{
	
	public void revolve(){
		System.out.println("Luxury Tyre");
	}
}

class CommonTyre implements Tyre{
	
	public void revolve(){
		System.out.println("Common Tyre");
	}
}
