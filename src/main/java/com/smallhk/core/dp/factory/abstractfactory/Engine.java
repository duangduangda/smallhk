package com.smallhk.core.dp.factory.abstractfactory;

public interface Engine {

	public void run();
	
	public void start();
}

class LuxuryEngine implements Engine{

	public void run() {
		System.out.println("转得快");
	}

	public void start() {
		System.out.println("启动快");
	}
	
} 

class CommonEngine implements Engine{

	public void run() {
		System.out.println("转得慢");
	}

	public void start() {
		System.out.println("启动慢");
	}
	
} 
