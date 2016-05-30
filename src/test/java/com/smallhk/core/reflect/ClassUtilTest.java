package com.smallhk.core.reflect;

import org.junit.Test;

public class ClassUtilTest {

	@Test
	public void testPrintClassMessage() {
		String s = "321321";
		
		ClassUtil.printClassDetailMessage(s);
		
		ClassUtil.printMethodMessage(s);
		
	}
	
	@Test
	public void testPrintFieldMessage(){
		String s = "321321";
		ClassUtil.printFieldMessage(s);
	}

}
