package org.dean.duck.core.concurrent.atomic;

import org.dean.duck.core.concurrent.AtomicDemo;

public class AtomicDemoTest {

	public static void main(String[] args) {
		final AtomicDemo demo = new AtomicDemo();
		for(int i = 0;i < 500;i++){
			new Thread(new Runnable(){
				public void run() {
					demo.increase();
				}
				
			}).start();
		}
		
		while(Thread.activeCount() > 1){
			Thread.yield();
		}
		
		System.out.println(demo.getNumber());
	}

}
