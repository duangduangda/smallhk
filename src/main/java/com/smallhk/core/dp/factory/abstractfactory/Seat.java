package com.smallhk.core.dp.factory.abstractfactory;

public interface Seat {

	public void message();
}

class LuxurySeat implements Seat{

	public void message() {
		System.out.println("舒服");
	}
	
}

class CommonSeat implements Seat{
	
	public void message(){
		System.out.println("不舒服");
	}
}