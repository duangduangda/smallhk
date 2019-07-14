package org.dean.duck.core.concurrent.atomic;

/**
 * @description 模拟CAS操作
 * @author eric
 * @since 2016年5月9日 下午3:17:13
 * @version 1.0
 *
 */
public class SimulatedCAS {
	private int value;
	
	public synchronized int get(){
		return value;
	}
	
	public synchronized int compareAndSwap(int expectedValue,int newValue){
		int oldValue = value;
		if(expectedValue == oldValue){
			value = newValue;
		}
		return oldValue;
	}
	
	public synchronized boolean compareAndSet(int expectedValue,int newValue){
		return (expectedValue == compareAndSwap(expectedValue, newValue));
	}
}
