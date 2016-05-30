package com.smallhk.core.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileDemo {

	private volatile int number = 0;
	
	private Lock lock = new ReentrantLock();
	
	public int getNumber(){
		return this.number;
	}
	
	public void increasement(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.number++;
	}
	
	public void syncronizeIncrease(){
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(this){
			this.number++;
		}
	}
	
	public void lockIncrease(){
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			lock.lock();
			this.number++;
		} finally {
			lock.unlock();
		}
	}
}
