package org.dean.duck.core.concurrent.queue;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Title. <br> Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/11
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class ArrayBlockingQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ArrayBlockingQueueDemo-%d")
				.setDaemon(false).build();

		ExecutorService service = new ThreadPoolExecutor(2, 2, 1,
				TimeUnit.SECONDS, new ArrayBlockingQueue<>(6), threadFactory, new ThreadPoolExecutor.DiscardPolicy());
//        ExecutorService service = new ThreadPoolExecutor(2,2,1,
//                TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(),threadFactory,new ThreadPoolExecutor.DiscardPolicy());

		for (int i = 0; i < 20; i++) {
			service.submit(() -> System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName()));
		}

		//手动调用shutdown并不会立即关闭ExecutorService，而是等待ExecutorService中所有的任务完成，并且提交之后，才会关闭的。（所以手动调用shotdown方法，可以不必担心存在剩余任务没有执行的情况）
		service.awaitTermination(2, TimeUnit.SECONDS);
		service.shutdownNow();

	}

}
