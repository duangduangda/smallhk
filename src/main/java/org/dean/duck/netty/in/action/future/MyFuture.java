package org.dean.duck.netty.in.action.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Title. <br> Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/13
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class MyFuture {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Runnable task1 = () -> System.out.println("I am task one");

		Callable<Integer> task2 = () -> new Integer(600);

		Future<?> future1 = executorService.submit(task1);
		Future<Integer> future2 = executorService.submit(task2);
		System.out.println("task1 is completed?" + future1.isDone());
		System.out.println("task2 is completed?" + future2.isDone());
		while (future1.isDone()) {
			System.out.println("task1 completed....");
			break;
		}

		while (future2.isDone()) {
			System.out.println("return value key task2" + future2.get());
			break;
		}
		if (!executorService.isShutdown()) {
			executorService.shutdownNow();
		}
	}
}
