package com.smallhk.core.concurrent;

import com.smallhk.core.concurrent.VolatileDemo;

public class VolatileDemoTest {


	public static void main(String[] args) {
		final VolatileDemo demo = new VolatileDemo();
		for(int i = 0;i < 500;i++){
			new Thread(new Runnable(){
				public void run() {
//					demo.increasement();
//					demo.syncronizeIncrease();
					demo.lockIncrease();
				}
				
			}).start();
		}
		//如果还有子线程在运行，主线程就让出CPU资源，
		//直到所有的子线程都运行完了，主线程再继续往下执行
		while(Thread.activeCount() > 1){
			Thread.yield();
		}
		
		System.out.println(demo.getNumber());
	}

}
