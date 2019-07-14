package org.dean.duck.core.dp.state;

public class StoppingState implements State {
	@Override
	public void handle() {
		System.out.println("stopping..");
	}

}
