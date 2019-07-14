package org.dean.duck.core.dp.factory.factorymethod;

import org.dean.duck.core.dp.factory.Byd;
import org.dean.duck.core.dp.factory.Car;

public class BydFactory implements CarFactory{

	public Car createCar() {
		return new Byd();
	}
	
}
