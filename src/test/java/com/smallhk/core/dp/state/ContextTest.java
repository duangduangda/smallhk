package com.smallhk.core.dp.state;

import org.junit.Test;

public class ContextTest {

	@Test
	public void test() {
		Context context = new Context(new StartingState());
		context.request();
		context = new Context(new RunningState());
		context.request();
		context = new Context(new StoppingState());
		context.request();
	}

}
