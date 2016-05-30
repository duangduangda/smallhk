package com.smallhk.core.dp.factory.factorymethod;

import com.smallhk.core.dp.factory.Audi;
import com.smallhk.core.dp.factory.Car;

public class AudiFactory implements CarFactory{

	public Car createCar() {
		return new Audi();
	}
	
}
