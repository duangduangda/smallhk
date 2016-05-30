package com.smallhk.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class CloneTest {

	@Test
	public void test() {
		Dog d1 = new Dog("blot", 9.8);
		Dog d2 = (Dog) d1.clone();
		assertTrue(d1.equals(d2));
		assertFalse(d1 == d2);
	}

}
