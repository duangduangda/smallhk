package org.dean.duck.core.rpc.oio.server.api;

import lombok.extern.slf4j.Slf4j;
import mockit.Expectations;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

@Slf4j
public class HelloServiceTest {

	@Tested
	HelloServiceImpl helloService;

	@DataProvider(name = "test1")
	public static Object[][] rangeData() {
		return new Object[][]{
				{"china"},
				{"usa"},
				{"japan"}
		};
	}

	@Test(dataProvider = "test1")
	public void testSayHello(String input) {
		new Expectations() {{
			helloService.sayHello(input);
			result = "hello,china";
			result = "hello,usa";
			result = "hello,japan";
		}};
		Assert.assertEquals("hello,china", helloService.sayHello("china"));
		Assert.assertEquals("hello,usa", helloService.sayHello("usa"));
		Assert.assertEquals("hello,japan", helloService.sayHello("japan"));
	}

	@Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Param 'info' can not be null")
	public void testSayHelloFailure1() {
		helloService.sayHello(null);
	}

	@Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Param .*")
	public void testSayHelloFailure2() {
		helloService.sayHello("");
	}

	@Test
	public void testSayHello2() {
		new MockUp<HelloService>() {
			@Mock
			public String sayHello(Invocation invocation, String info) {
				Assert.assertTrue(Objects.nonNull(info));
				Assert.assertTrue(!info.isEmpty());
				return invocation.proceed(info);
			}
		};
		Assert.assertNotNull(helloService.sayHello("abc"));
		Assert.assertEquals("hello,abc", helloService.sayHello("abc"));
	}

	@Test
	public void testSayHello3() {
		new MockUp<HelloService>(HelloServiceImpl.class) {
			@Mock
			public String sayHello(Invocation invocation, String info) {
				if (null == info || info.isEmpty()) {
					return "hello,abc";
				}
				return invocation.proceed(info);
			}
		};
		Assert.assertEquals("hello,abc", helloService.sayHello(null));
		Assert.assertEquals("hello,abc", helloService.sayHello(""));
		Assert.assertEquals("hello,abc", helloService.sayHello("abc"));
		Assert.assertEquals("hello,world", helloService.sayHello("world"));
	}
}