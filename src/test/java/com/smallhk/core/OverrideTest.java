package com.smallhk.core;

import org.junit.Test;

public class OverrideTest {
	
	@Test
	public void testOverloadInfo(){
		OverloadAndOverride ot = new OverloadAndOverride();
		ot.info("test", 5);
	}
	
	@Test
	public void testOverloadSend(){
		OverloadAndOverride oao = new OverloadAndOverride();
		oao.send(null, 5);
	}
}
