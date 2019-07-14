package org.dean.duck.core.concurrent.threadpool;

import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.AfterClass;
import org.junit.Test;

public class FutureTaskExecutorTest {

	private static ExecutorService executor = Executors.newFixedThreadPool(3);
	
	@Test
	public void testRunnabelFutureResult() throws InterruptedException, ExecutionException {
		Runnable task = new Runnable() {
			public void run() {
				System.out.println("Runnable running .....");
			}
		};
		Future<?> future = executor.submit(task);
		assertNull(future.get());
	}
	
	@Test
	public void testCallableFutureResult() throws InterruptedException, ExecutionException{
		Callable<String> task = new Callable<String>() {
			public String call() throws Exception {
				return "result=task1";
			}
		};
		Future<String> future = executor.submit(task);
		System.out.println(future.get());
	}
	
	@AfterClass
	public static void shutdownExecutors(){
		executor.shutdown();
	}
}
