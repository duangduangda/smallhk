package org.dean.duck.core.dp.factory;

import org.dean.duck.core.dp.factory.abstractfactory.CarFactory;
import org.dean.duck.core.dp.factory.abstractfactory.Engine;
import org.dean.duck.core.dp.factory.abstractfactory.LuxuryCarFactory;
import org.dean.duck.core.dp.factory.factorymethod.AudiFactory;
import org.dean.duck.core.dp.factory.factorymethod.BydFactory;
import org.dean.duck.core.dp.factory.simplefactory.SimpleCarFactory;
import org.junit.Test;

public class FactoryPatternTest {

	@Test
	public void testWithoutFactory() {
		Car c1 = new Audi();
		Car c2 = new Byd();
		c1.run();
		c2.run();
	}
	
	@Test
	public void testSimpleFactory(){
		Car c1 = SimpleCarFactory.createCar("奥迪");
		Car c2 = SimpleCarFactory.createCar("比亚迪");
		c1.run();
		c2.run();
	}
	
	@Test
	public void testFactoryMethod(){
		Car c1 = new AudiFactory().createCar();
		Car c2 = new BydFactory().createCar();
		
		c1.run();
		c2.run();
	}
	
	@Test
	public void testAbstractFactory(){
		CarFactory factory = new LuxuryCarFactory();
		Engine e = factory.createEngine();
		e.start();
		e.run();
	}
}
