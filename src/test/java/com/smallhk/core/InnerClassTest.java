package com.smallhk.core;

import org.junit.Test;

public class InnerClassTest {

	@Test
	public void test() throws InstantiationException, IllegalAccessException {
		Outer outer = new Outer();
		outer.test();
	}

}
