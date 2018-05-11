package com.smallhk.core.dp.state;

public class StartingState implements State {

	@Override
	public void handle() {
		System.out.println("starting..");
	}

}
