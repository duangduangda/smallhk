package org.dean.duck.distribute.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步创建节点
 *
 * @author eric
 */
public class CreateNodeBackGround {
	static String path = "/base_path";
	static CountDownLatch countDownLatch = new CountDownLatch(2);
	static ExecutorService executorService = Executors.newFixedThreadPool(2);
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("localhost:2181")
			.sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3))
			.build();

	public static void main(String[] args) throws Exception {
		client.start();
		System.out.println("Main thread: " + Thread.currentThread().getName());
		// 使用自定义的线程池
		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
				.inBackground((curatorFramework, curatorEvent) -> {
					System.out.println("user-defined-thread-name:" + Thread.currentThread().getName() + "event.code" + curatorEvent.getResultCode());
					countDownLatch.countDown();
				}, executorService)
				.forPath(path, "init".getBytes());

		// 使用默认的线程池
		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
				.inBackground((curatorFramework, event) -> {
					System.out.println("default-thread-name:" + Thread.currentThread().getName() + "event.code" + event.getResultCode());
					countDownLatch.countDown();
				})
				.forPath(path, "init".getBytes());

		countDownLatch.await();
		executorService.shutdown();
	}
}
