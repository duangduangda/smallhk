package com.smallhk.core;

import org.junit.Test;

public class StaticTest {

	public static void info(){
		System.out.println("static info method");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testStaticHandler1(){
		StaticTest s = null;
		s.info();
	}

	@SuppressWarnings("static-access")
	@Test
	public void testStaticHandler2(){
		Animal animal = new Animal();
		animal.info();
		
		Dragon dragon = new Dragon();
		dragon.info();
	}
}
