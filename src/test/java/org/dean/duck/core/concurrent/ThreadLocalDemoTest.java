package org.dean.duck.core.concurrent;

import org.dean.duck.core.concurrent.threadlocal.ThreadLocalDemo;

public class ThreadLocalDemoTest {
	public static void main(String[] args){
		for(int i = 0;i < 5;i++){
			new Thread(new ThreadLocalDemo.MyThread(i)).start();
		}
	}

}
