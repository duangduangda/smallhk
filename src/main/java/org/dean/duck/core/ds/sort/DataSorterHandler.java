package org.dean.duck.core.ds.sort;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DataSorterHandler implements InvocationHandler{
	
	private Object target;
	
	public DataSorterHandler(Object target){
		this.target = target;
	}

	/**
	 * 排序结束后提示语
	 */
	public void after(Method m) {
		System.out.println(m.getName() + "排序算法结束.....");

	}

	/**
	 * 开始排序提示语
	 */
	public void before(Method m) {
		System.out.println(m.getName() + "排序算法开始....");
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before(method);
		Object o =  method.invoke(target, args);
		after(method);
		return o;
	}
}
