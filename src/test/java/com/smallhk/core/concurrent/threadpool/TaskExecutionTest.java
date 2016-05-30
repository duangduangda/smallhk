package com.smallhk.core.concurrent.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;

public class TaskExecutionTest {
	private static final int MAX_NUM = 10;
	private static final Executor executor = Executors.newFixedThreadPool(MAX_NUM);
	
	@Test
	public void testRunnableExecutor(){
		while(true){
			Runnable task = new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + "----");
				}
			};
			executor.execute(task);
		}
	}
}
