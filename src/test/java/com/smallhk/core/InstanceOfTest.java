package com.smallhk.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class InstanceOfTest {

	@Test
	public void testInheritHandler() {
		Programer p = new Programer();
		assertTrue(p instanceof Programer);
		assertTrue( p instanceof Employee);
		
		Employee e = new Employee();
		assertTrue(e instanceof Employee);
		assertFalse(e instanceof Programer);
	}
	
	@Test
	public void testNullInstanceOfHandler(){
		String s = null;
		assertFalse(s instanceof String);
		assertFalse(s instanceof Object);
	}

}
