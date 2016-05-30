package com.smallhk.core.dp.factory.abstractfactory;

public class CommonCarFactory implements CarFactory {

	public Engine createEngine() {
		return new CommonEngine();
	}

	public Seat createSeat() {
		return new CommonSeat();
	}

	public Tyre createTyre() {
		return new CommonTyre();
	}

}
