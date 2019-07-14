package org.dean.duck.core.dp.factory.factorymethod;

import org.dean.duck.core.dp.factory.Audi;
import org.dean.duck.core.dp.factory.Car;

public class AudiFactory implements CarFactory{

	public Car createCar() {
		return new Audi();
	}
	
}
