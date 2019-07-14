package org.dean.duck.core.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

	private AtomicInteger number = new AtomicInteger(0);
	
	public void increase() {
		number.incrementAndGet();
	}

	public int getNumber() {
		return number.get();
	}

}
