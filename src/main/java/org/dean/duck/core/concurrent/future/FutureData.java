package org.dean.duck.core.concurrent.future;

/**
 * 
 * @author eric
 *
 */
public class FutureData implements Data {
	private RealData realData = null;
	private boolean isReady = false;
	
	public synchronized void set(RealData realData){
		if(isReady){
			return;
		}
		this.realData = realData;
		isReady = true;
		notifyAll();
	}
	
	public synchronized String get() {
		while(!isReady){
			try{
				wait();
			}catch (Exception e) {
			}
		}
		return realData.get();
	}

}
