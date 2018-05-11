package com.smallhk.core.dp.state;

public class RunningState implements State{
	@Override
	public void handle() {
		System.out.println("running..");		
	}

}
