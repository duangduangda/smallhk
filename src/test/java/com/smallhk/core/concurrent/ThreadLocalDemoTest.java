package com.smallhk.core.concurrent;

import com.smallhk.core.concurrent.threadlocal.ThreadLocalDemo.MyThread;

public class ThreadLocalDemoTest {
	public static void main(String[] args){
		for(int i = 0;i < 5;i++){
			new Thread(new MyThread(i)).start();
		}
	}

}
