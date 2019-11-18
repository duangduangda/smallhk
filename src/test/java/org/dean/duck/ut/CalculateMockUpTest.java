package org.dean.duck.ut;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class CalculateMockUpTest {

	@Test
	public void testAdd() {
		new MockUp<Calculate>(Calculate.class) {
			@Mock
			public Integer add(Integer a, Integer b) {
				return Optional.ofNullable(a).orElse(0)
						* Optional.ofNullable(b).orElse(0);
			}
		};
		Calculate calculate = new Calculate();
		Assert.assertTrue(2 == calculate.add(1, 2));
		// 其他方法不受影响
		Assert.assertTrue(-1 == calculate.substract(1, 2));
	}
}
