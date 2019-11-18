package org.dean.duck.ut;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

/**
 * HelloJMockit Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10月 21, 2019</pre>
 */
@RunWith(JMockit.class)
public class HelloJMockitTest {
	/**
	 * Method: sayHello()
	 */
	@Test
	public void testSayHello(@Mocked HelloJMockit helloJMockit) {
		// 录制record:先录制某类/对象的某个方法调用，在当输入什么时，返回什么
		new Expectations() {
			{
				helloJMockit.sayHello();
				result = "hello,eric";
			}

		};

		// 重放 replay:重放测试逻辑
		String msg = helloJMockit.sayHello();
		Assert.assertTrue("hello,eric".equals(msg));

		// 验证 verification:重放后的验证。比如验证某个方法有没有被调用，调用多少次。
		new Verifications() {
			{
				helloJMockit.sayHello();
				times = 1;
			}
		};
	}

	@Test
	public void sayHelloCH() {
		new Expectations(Locale.class) {
			{
				Locale.getDefault();
				result = Locale.CHINA;
			}
		};
		Assert.assertTrue("你好世界".equals(new HelloJMockit().sayHello()));
	}

	@Test
	public void sayHelloUS() {
		new Expectations(Locale.class) {
			{
				Locale.getDefault();
				result = Locale.US;
			}
		};
		Assert.assertTrue("hello,world".equals(new HelloJMockit()
				.sayHello()));
	}

	@Test
	public void sayHelloNonStrictExpect() {

	}
} 
