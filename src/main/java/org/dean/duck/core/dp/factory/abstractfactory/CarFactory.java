package org.dean.duck.core.dp.factory.abstractfactory;

public interface CarFactory {
	
	public Engine createEngine();
	
	public Seat createSeat();
	
	public Tyre createTyre();
}
