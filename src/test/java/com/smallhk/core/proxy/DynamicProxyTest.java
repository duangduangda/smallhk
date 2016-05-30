package com.smallhk.core.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class DynamicProxyTest {

	@Test
	public void test() {
		Hello target = new HelloImpl();
		DynamicProxy proxy = new DynamicProxy(target);
		Hello dest = (Hello) Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				proxy);
		dest.sayHello();
	}

}
