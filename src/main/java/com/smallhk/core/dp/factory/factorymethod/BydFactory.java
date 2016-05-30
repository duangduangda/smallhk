package com.smallhk.core.dp.factory.factorymethod;

import com.smallhk.core.dp.factory.Byd;
import com.smallhk.core.dp.factory.Car;

public class BydFactory implements CarFactory{

	public Car createCar() {
		return new Byd();
	}
	
}
