package org.dean.duck.core.concurrent.future;

public class Client {
	
	public Data request(final String queryStr){
		final FutureData future = new FutureData();
		new Thread(new Runnable() {
			
			public void run() {
				RealData realData = new RealData(queryStr);
				future.set(realData);
			}
		}).start();
		return future;
	}

}
