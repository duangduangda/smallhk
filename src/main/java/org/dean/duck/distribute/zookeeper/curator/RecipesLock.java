package org.dean.duck.distribute.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 使用curator实现分布式锁功能
 *
 * @author eric
 */
public class RecipesLock {
	public static void main(String[] args) {
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("localhost:2181")
				.sessionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();
		client.start();
		String lockPath = "/curator-recipes";
		CountDownLatch countDownLatch = new CountDownLatch(1);
		InterProcessMutex interProcessMutex = new InterProcessMutex(client, lockPath);
		for (int i = 0; i < 30; i++) {
			new Thread(() -> {
				try {
					countDownLatch.await();
					interProcessMutex.acquire();
				} catch (Exception e) {
				}
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
				String orderNo = simpleDateFormat.format(new Date());
				System.out.println("生成的订单号为：" + orderNo);
				try {
					interProcessMutex.release();
				} catch (Exception e) {

				}
			}).start();
		}
		countDownLatch.countDown();

	}
}
