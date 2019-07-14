package org.dean.duck.core.dp.state;

public class RunningState implements State{
	@Override
	public void handle() {
		System.out.println("running..");		
	}

}
