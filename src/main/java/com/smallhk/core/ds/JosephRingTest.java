package com.smallhk.core.ds;

import org.junit.Test;

public class JosephRingTest {

	@Test
	public void test() {
		JosephRing ring = new JosephRing();
		int []a = {2,5,6,7};
		ring.handle(a, 3);
	}

}
