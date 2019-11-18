package org.dean.duck.ut;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Calculate Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10月 21, 2019</pre>
 */
@RunWith(JMockit.class)
public class CalculateTest {

	/**
	 * Method: add(int a, int b)
	 */
	@Test
	public void testAdd(@Mocked Calculate calculate) throws Exception {
		new Expectations() {
			{
				calculate.add(1, null);
				result = 1;
				calculate.add(1, 2);
				result = 3;
			}
		};
		int result = calculate.add(1, null);
		Assert.assertEquals(1, result);
		result = calculate.add(1, 2);
		Assert.assertEquals(3, result);
		new Verifications() {
			{
				calculate.add(1, null);
				times = 1;
			}
		};
		new Verifications() {
			{
				calculate.add(1, 2);
				times = 1;
			}
		};
	}

	/**
	 * Method: substract(int a, int b)
	 */
	@Test
	public void testSubstract(@Mocked Calculate calculate) throws Exception {
		new Expectations() {
			{
				calculate.substract(1, null);
				result = 1;
				calculate.substract(1, 2);
				result = -1;
				calculate.substract(null, null);
				result = 0;
				calculate.substract(null, 1);
				result = -1;
				calculate.substract(3, 1);
				result = 2;
			}
		};
		int result = calculate.substract(1, null);
		Assert.assertEquals(1, result);
		result = calculate.substract(1, 2);
		Assert.assertEquals(-1, result);
		result = calculate.substract(null, 1);
		Assert.assertEquals(-1, result);
		result = calculate.substract(null, null);
		Assert.assertEquals(0, result);
		result = calculate.substract(3, 1);
		Assert.assertEquals(2, result);
		new Verifications() {
			{
				calculate.substract(1, null);
				times = 1;
			}
		};
		new Verifications() {{
			calculate.substract(1, 2);
			times = 1;
		}};
		new Verifications() {
			{
				calculate.substract(null, 1);
				times = 1;
			}
		};

		new Verifications() {
			{
				calculate.substract(null, null);
				times = 1;
			}
		};

		new Verifications() {{
			calculate.substract(3, 1);
			times = 1;
		}};
	}


}
