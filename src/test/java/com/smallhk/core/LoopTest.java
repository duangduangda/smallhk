package com.smallhk.core;

import org.junit.Test;

public class LoopTest {

	
	@Test
	public void test() {
		//此处由于cat是局部变量，如果for循环如果省略大括号，将会出现编译错误
		for(int i = 0;i < 9;i++){
			Cat cat = new Cat();
		}
		System.out.println(Cat.getInstanceCount());
		
	}

}
