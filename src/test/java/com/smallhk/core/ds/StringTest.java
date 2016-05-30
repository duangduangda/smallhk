package com.smallhk.core.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class StringTest {

	@Test
	public void testDoubleReverseStr(){
		String s = "Hello World";
		assertEquals(5, s.indexOf(" "));
		String result = StringHandler.middleReverse(s);
		assertNotNull(result);
		assertEquals("olleH dlroW",result);
	} 
	
	@Test
	public void testReverseStr(){
		String s = "Hello";
		String result = StringHandler.doReverse(s.toCharArray(), 0, s.length() - 1);
		assertEquals("olleH", result);
	}
}
